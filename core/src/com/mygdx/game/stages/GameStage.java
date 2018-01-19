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


import java.util.Random;

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
    private Rectangle screenLeftSide;
    private Vector3 touchPoint;


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
//        createWall();
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
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        Gdx.app.log("COUNT", "BODIES: " + world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            update(body);
        }

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        //TODO: Implement interpolation

    }

    private void update(Body body) {
        if (!BodyUtils.bodyInBounds(body)) {
            if (BodyUtils.bodyIsEnemy(body) && !runner.isHit()) {
                createEnemy();
            } else if (BodyUtils.bodyIsPlatform(body) && runner.isOnPlatform()){
//                createPlatform();
            }
            world.destroyBody(body);
        }

       // Gdx.app.log("Pozycja skaly lewej", new Float(Rock.getPosition()).toString() );

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
        Platform[] platforms = new Platform[Constants.PLATFORM_AMOUNT];
        platforms[0] = new Platform(WorldUtils.createPlatform(world, 0));
        addActor(platforms[0]);
        for(int i = 1; i < Constants.PLATFORM_AMOUNT; i++){

            do { randShift = WorldUtils.generateRandomShift();
            }while((randShift + LastPlatformY ) < 2);

            platforms[i] = new Platform(WorldUtils.createPlatform(world, randShift));
            addActor(platforms[i]);
        }
        // TODO: Reset positions of PlatformType


    }
    private void createFallingRock() {
        Rock = new FallingRock(WorldUtils.createFallingRock(world));
        addActor(Rock);
    }

    private void createBullet() {
        Gdx.app.log("Check Position of Runner", runner.getX() + " : " + runner.getY());
        Bullet bullet = new Bullet(WorldUtils.createBullet(world, runner.getX(), runner.getY()));
        addActor(bullet);
    }



    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    public boolean touchDown(int x, int y, int pointer, int button) {

        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);

        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            runner.jump();
        }
        else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
//            runner.move();
            createBullet();
        }

        return super.touchDown(x, y, pointer, button);
    }

    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }
    private boolean leftSideTouched(float x, float y) {
        return screenLeftSide.contains(x, y);
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