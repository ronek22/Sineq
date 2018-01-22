package com.mygdx.game.stages;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.game.GameMain;
import com.mygdx.game.actors.Background;
import com.mygdx.game.actors.Bullet;
import com.mygdx.game.actors.FallingRock;
import com.mygdx.game.actors.Ground;
import com.mygdx.game.actors.Platform;
import com.mygdx.game.actors.Runner;
import com.mygdx.game.actors.Score;
import com.mygdx.game.actors.SpikeGround;
import com.mygdx.game.enums.Difficulty;
import com.mygdx.game.enums.GameState;
import com.mygdx.game.enums.PlatformType;
import com.mygdx.game.screens.Menu;
import com.mygdx.game.screens.OverScreen;
import com.mygdx.game.utils.BodyUtils;
import com.mygdx.game.utils.Constants;
import com.mygdx.game.utils.GameManager;
import com.mygdx.game.utils.WorldUtils;
import com.mygdx.game.actors.Enemy;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.actors.Wall;



import static com.mygdx.game.utils.WorldUtils.LastPlatformY;


public class GameStage extends Stage implements ContactListener {

    // This will be our viewport measurements while working with the debug renderer
    GameMain game;
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private World world;
    private Ground ground;
    private Runner runner;
    private Wall left_wall;
    private Wall right_wall;
    private Enemy enemy;
    private SpikeGround spikes;

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
    private Array<FallingRock> rocks = new Array<FallingRock>();

    // how many enemies
    private boolean shoot = false;
    private boolean makeEnemy = false;
    private boolean gameOver = false;
    private boolean levelPlatform = true;
    private boolean levelEnemy = false;
    private int countEnemies = 0;
    private int MAX_PLATFORM;
    private int MAX_ENEMIES;
    private Score score;
    private float totalTimePassed;


    public GameStage(GameMain game) {
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        this.game = game;
        setUpGame();
        setUpWorld();
        setUpCamera();
        setUpScore();
        setupTouchControlAreas();
        renderer = new Box2DDebugRenderer();
    }

    public void setUpGame(){
        GameManager.getInstance().setGameState(GameState.RUNNING);
        GameManager.getInstance().setDifficulty(Difficulty.DIF_1);

        Difficulty current = GameManager.getInstance().getDifficulty();

        MAX_ENEMIES = current.getAmountEnemies();
        MAX_PLATFORM = current.getAmountPlatform();

    }

    private void setUpWorld(){
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        setUpGround();
        setUpRunner();
        createWall();

    }

    private void setUpBackground(){
        addActor(new Background());
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world));
        addActor(runner);
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setUpScore(){
        Rectangle scoreBounds = new Rectangle(getCamera().viewportWidth * 47 / 64,
                getCamera().viewportHeight * 57 / 64, getCamera().viewportWidth / 4,
                getCamera().viewportHeight / 8);
        score = new Score(scoreBounds);
        addActor(score);
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenBottomLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight / 2);
        screenTopLeftSide = new Rectangle(0, getCamera().viewportHeight / 2, getCamera().viewportWidth / 2, getCamera().viewportHeight / 2);
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    public void updateDifficulty(){
        if (GameManager.getInstance().isMaxDifficulty()) {
            return;
        }

        Difficulty current = GameManager.getInstance().getDifficulty();

        int nextDifficulty = current.getLevel() + 1;
        String difficultyName = "DIF_" + nextDifficulty;
        GameManager.getInstance().setDifficulty(Difficulty.valueOf(difficultyName));
        current = GameManager.getInstance().getDifficulty();
        MAX_ENEMIES = current.getAmountEnemies();
        MAX_PLATFORM = current.getAmountPlatform();
        runner.onDifficultyChange(current);
        score.setMultiplier(GameManager.getInstance().getDifficulty().getScoreMultiplier());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameManager.getInstance().getGameState() == GameState.PAUSED) return;

        if (GameManager.getInstance().getGameState() == GameState.RUNNING) {
            totalTimePassed += delta;
        }

        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
            controlDesktop(delta);
        }

        //   System.out.println("BODIES: " + world.getBodyCount());
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

        cleanWorld();
        gameLoop();

    }

    private void update(Body body) {
        if (!BodyUtils.bodyInBounds(body) ) {
            if ((BodyUtils.bodyIsEnemy(body) && !runner.isHit())) {
                // zly warunek po zabiciu juz sie nie pokaza nastepni
                makeEnemy = true;
            } else if (BodyUtils.bodyIsPlatform(body) && runner.isOnPlatform()){
                //createPlatform();
            }
            toBeDeleted.add(body);
        }

    }


    private void createEnemy() {
        enemy = new Enemy(WorldUtils.createEnemy(world));
        enemy.getUserData().setLinearVelocity(
                GameManager.getInstance().getDifficulty().getEnemyLinearVelocity());
        countEnemies++;
        addActor(enemy);
    }

    private void createWall(){
        right_wall = new Wall(WorldUtils.createRightWall(world));
        left_wall = new Wall(WorldUtils.createLeftWall(world));
        addActor(right_wall);
        addActor(left_wall);
    }



    private void createPlatforms() {
        float x;
        float randShift;
        platforms.add(new Platform(WorldUtils.createPlatform(world, 0)));
        platforms.get(platforms.size - 1).getUserData().setLinearVelocity(
                GameManager.getInstance().getDifficulty().getPlatformLinearVelocity()
        );
        addActor(platforms.get(platforms.size - 1));
        x = WorldUtils.LastPlatformX;

        createFallingRock(false);
        createFallingRock(true);

        for(int i = 1; i < MAX_PLATFORM; i++){

            do { randShift = WorldUtils.generateRandomShift();
            }while((randShift + LastPlatformY ) < 2);

            platforms.add(new Platform(WorldUtils.createPlatform(world, randShift)));
            platforms.get(platforms.size - 1).getUserData().setLinearVelocity(
                    GameManager.getInstance().getDifficulty().getPlatformLinearVelocity()
            );
            addActor(platforms.get(platforms.size - 1));
        }
      //  System.out.println("X: " + x);
        spikes = new SpikeGround(WorldUtils.createSpikes(world, x, WorldUtils.LastPlatformX));
        spikes.getUserData().setLinearVelocity(
                GameManager.getInstance().getDifficulty().getPlatformLinearVelocity()
        );
        addActor(spikes);

        PlatformType.reset();

    }

    private void createFallingRock(boolean next) {
        rocks.add(new FallingRock(WorldUtils.createFallingRock(world, next)));
        addActor(rocks.get(rocks.size-1));
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

        // ===== ENEMY ==========
        if(enemy != null) {
            if(toBeDeleted.contains(enemy.getBody(), false)){
                enemy.getBody().setUserData(null);
                enemy.addAction(Actions.removeActor());
                enemy.remove();
            }
        }


        // Spikes

        if(toBeDeleted.contains(spikes.getBody(), false)){
            spikes.getBody().setUserData(null);
            spikes.addAction(Actions.removeActor());
            spikes.remove();
        }
        // ======= ROCKS =========
        for(FallingRock rock : rocks) {
            if (toBeDeleted.contains(rock.getBody(), false)) {
                indexes.add(rocks.indexOf(rock, true));
                rock.getBody().setUserData(null);
                rock.addAction(Actions.removeActor());
            }
        }

        for(int ind : indexes){
           rocks.get(ind).remove();
           rocks.removeIndex(ind);
        }
        indexes.clear();

        // Runner
        if(toBeDeleted.contains(runner.getBody(), false)){
            runner.getBody().setUserData(null);
            runner.addAction(Actions.removeActor());
            runner.remove();
            gameOver = true;
        }


    }

    private void cleanWorld(){
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
    }

    private void gameLoop() {
        if(!world.isLocked() && levelPlatform){
            createPlatforms();
            levelPlatform = false;
        }

        if(emptyLevel() && canMakeEnemies()){
            createEnemy();
        }

        if(emptyLevel() && !canMakeEnemies()){
            makeEnemy = false;
            levelPlatform = true;
            countEnemies = 0;
            updateDifficulty();
        }

        if(!world.isLocked() && levelEnemy && canMakeEnemies()){
            levelEnemy = false;
        }



        if(!world.isLocked() && shoot){
            createBullet();
            shoot = false;
        }

        if(!world.isLocked() && makeEnemy && canMakeEnemies()){
            createEnemy();
            makeEnemy = false;
        }

        if(!world.isLocked() && gameOver){
            System.out.println("GAME OVER");
            onGameOver();
        }
    }


    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);

    }

    private boolean canMakeEnemies() {
        return countEnemies < MAX_ENEMIES && emptyLevel();
    }

    private boolean emptyLevel() {
        return world.getBodyCount() == 4;
    }

    private void onGameOver() {
        GameManager.getInstance().setGameState(GameState.OVER);
        GameManager.getInstance().submitScore(score.getScore());
        ((Game)Gdx.app.getApplicationListener()).setScreen(new OverScreen(game, score.getScore()));
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

    public void controlDesktop(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            runner.jump();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            runner.move();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            shoot = true;
        }
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

        if (BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) {
            runner.hit();
            toBeDeleted.add(a);
        } else if(BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b)){
            runner.hit();
            toBeDeleted.add(b);
        } else if(BodyUtils.bodyIsRock(b) && BodyUtils.bodyIsRunner(a)){
            runner.hit();
            toBeDeleted.add(a);
        } else if(BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsSpikes(b)){
            runner.hit();
            toBeDeleted.add(a);
        } else if(BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsSpikes(b)){
            runner.hit();
            toBeDeleted.add(b);
        } else if((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {
            runner.landed();
        } else if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsPlatform(b)) ||
                (BodyUtils.bodyIsPlatform(a) && BodyUtils.bodyIsEnemy(a))){
            runner.platform();
            runner.landed();
//            System.out.println("LANDED ON PLATFORM");
        }
        else if((BodyUtils.bodyIsBullet(a) && BodyUtils.bodyIsPlatform(b))){
            toBeDeleted.add(a);
        } else if(BodyUtils.bodyIsPlatform(a) && BodyUtils.bodyIsBullet(b)) {
            toBeDeleted.add(b);
        } else if((BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsBullet(b)) ||
                ((BodyUtils.bodyIsBullet(a) && BodyUtils.bodyIsEnemy(b)))){
            makeEnemy = true;
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