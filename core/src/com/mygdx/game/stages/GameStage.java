package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.actors.Bullet;
import com.mygdx.game.actors.FallingRock;
import com.mygdx.game.actors.Ground;
import com.mygdx.game.actors.Platform;
import com.mygdx.game.actors.Runner;
import com.mygdx.game.utils.BodyUtils;
import com.mygdx.game.utils.Constants;
import com.mygdx.game.utils.WorldUtils;
import com.mygdx.game.actors.Enemy;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.actors.Wall;



import static com.mygdx.game.utils.WorldUtils.LastPlatformY;


public class GameStage extends Stage implements ContactListener {

    // This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private World world;
    private Ground ground;
    private Runner runner;
    private Wall left_wall;
    private Wall right_wall;
    private FallingRock Rock;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    // for controls
    private Rectangle screenRightSide;
    private Rectangle screenTopLeftSide;
    private Rectangle screenBottomLeftSide;
    private Vector3 touchPoint;

    private Array<Bullet> bullets = new Array<Bullet>();
    private Array<Platform> platforms = new Array<Platform>();
    private Array<Body> toBeDeleted = new Array<Body>();

    // how many enemies
    private boolean shoot = false;

    public GameStage() {
        setUpWorld();
        setupCamera();
        setupTouchControlAreas();
        renderer = new Box2DDebugRenderer();
    }

    private void setUpWorld(){
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpGround();
        setUpRunner();
        createWall();
//        createFallingRock();
//        createEnemy();
        createPlatforms();
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world));
        addActor(runner);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenBottomLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight / 2);
        screenTopLeftSide = new Rectangle(0, getCamera().viewportHeight / 2, getCamera().viewportWidth / 2, getCamera().viewportHeight / 2);
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(!world.isLocked()){
            Array<Body> bodies = new Array<Body>(world.getBodyCount());
            world.getBodies(bodies);
            for(Body body : bodies){
                update(body);
            }
        }

        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

    }

    private void update(Body body) {
        if (!BodyUtils.bodyInBounds(body) ) {
            if ((BodyUtils.bodyIsEnemy(body) && !runner.isHit())) {
                // zly warunek po zabiciu juz sie nie pokaza nastepni
                createEnemy();
            } else if (BodyUtils.bodyIsPlatform(body) && runner.isOnPlatform()){
                //createPlatform();
            }
            toBeDeleted.add(body);
        }

    }


    private void createEnemy() {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
        addActor(enemy);
    }
    private void createWall(){
        right_wall = new Wall(WorldUtils.createRightWall(world));
        left_wall = new Wall(WorldUtils.createLeftWall(world));
        addActor(right_wall);
        addActor(left_wall);
    }



    private void createPlatforms() {
        float randShift;
        platforms.add(new Platform(WorldUtils.createPlatform(world, 0)));
        addActor(platforms.get(platforms.size - 1));
        for(int i = 1; i < Constants.PLATFORM_AMOUNT; i++){

            do { randShift = WorldUtils.generateRandomShift();
            }while((randShift + LastPlatformY ) < 2);

            platforms.add(new Platform(WorldUtils.createPlatform(world, randShift)));
            addActor(platforms.get(platforms.size - 1));
        }
        // TODO: Reset positions of PlatformType


    }
    private void createFallingRock() {
        Rock = new FallingRock(WorldUtils.createFallingRock(world));
        addActor(Rock);
    }

    private void createBullet() {
        Gdx.app.log("Check Position of Runner", runner.getX() + " : " + runner.getY());
        bullets.add(new Bullet(WorldUtils.createBullet(world, runner.getX() + Constants.RUNNER_WIDTH, runner.getY())));
        addActor(bullets.get(bullets.size-1));

    }

    private void removeEmptyActors(){

        // ====== BULLETS =========
        Array<Integer> indexes = new Array<Integer>();
        for(Bullet bullet : bullets){
            if(toBeDeleted.contains(bullet.getBody(), false)){
                indexes.add(bullets.indexOf(bullet, true));
                bullet.getBody().setUserData(null);
                bullet.addAction(Actions.removeActor());
            }
        }

        for(int ind : indexes){
            bullets.get(ind).remove();
            bullets.removeIndex(ind);
        }
        indexes.clear();

        // ====== PLATFORMS =========
        for(Platform platform : platforms) {
            if (toBeDeleted.contains(platform.getBody(), false)) {
                indexes.add(platforms.indexOf(platform, true));
                platform.getBody().setUserData(null);
                platform.addAction(Actions.removeActor());
            }
        }

        for(int ind : indexes){
            platforms.get(ind).remove();
            platforms.removeIndex(ind);
        }
        indexes.clear();
    }



    @Override
    public void draw() {
        super.draw();

        if(toBeDeleted.size > 0){
            removeEmptyActors();
            if(!world.isLocked()) {
                for (Body body : toBeDeleted)
                {
                    world.destroyBody(body);
                    body.setUserData(null);
                }

            }
            toBeDeleted.clear();
        }

        if(!world.isLocked() && shoot){
            createBullet();
            shoot = false;
        }

        // Gdx.app.log("COUNT", "BULLETS: " + bullets.size);
        // Gdx.app.log("COUNT", "BODIES: " + world.getBodyCount());
        renderer.render(world, camera.combined);

    }

    public boolean touchDown(int x, int y, int pointer, int button) {

        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);

        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            runner.jump();
        }
        else if (leftTopSideTouched(touchPoint.x, touchPoint.y)) {
            runner.move();
        } else if (leftBottomSideTouched(touchPoint.x, touchPoint.y)) {
            shoot = true;
        }

        return super.touchDown(x, y, pointer, button);
    }

    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }
    private boolean leftTopSideTouched(float x, float y) {
        return screenTopLeftSide.contains(x, y);
    }
    private boolean leftBottomSideTouched(float x, float y){
        return screenBottomLeftSide.contains(x, y);
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))) {
            runner.hit();
        } else if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {
            runner.landed();
        } else if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsPlatform(b)) ||
                (BodyUtils.bodyIsPlatform(a) && BodyUtils.bodyIsEnemy(a))){
            runner.platform();
            runner.landed();
            System.out.println("LANDED ON PLATFORM");
        }
        else if((BodyUtils.bodyIsBullet(a) && BodyUtils.bodyIsPlatform(b))){
            toBeDeleted.add(a);
        } else if(BodyUtils.bodyIsPlatform(a) && BodyUtils.bodyIsBullet(b)) {
            toBeDeleted.add(b);
        } else if((BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsBullet(b)) ||
                ((BodyUtils.bodyIsBullet(a) && BodyUtils.bodyIsEnemy(b)))){
            toBeDeleted.add(a);
            toBeDeleted.add(b);
        }
    }



    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}