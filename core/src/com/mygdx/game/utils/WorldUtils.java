package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.enums.PlatformType;
import com.mygdx.game.physics.GroundUserData;
import com.mygdx.game.physics.PlatformUserData;
import com.mygdx.game.physics.RunnerUserData;
import com.mygdx.game.physics.EnemyUserData;
import com.mygdx.game.enums.EnemyType;

import java.util.Random;

public class WorldUtils {

    public static float LastPlatformY;

    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundUserData());
        shape.dispose();
        return body;
    }

    public static Body createPlatform(World world, float addY) {
        PlatformType platformType = RandomUtils.getRandomPlatformType();
        // dodawanie wartosci, aby platformy byly na roznych wysokosciach
        platformType.setX(platformType.getX() + Constants.PLATFORM_GAP);
        platformType.setY(platformType.getY() + addY);



        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(platformType.getX(), platformType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(platformType.getWidth() / 2, platformType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        FixtureDef fix = new FixtureDef();
        fix.shape = shape;
        fix.density = platformType.getDensity();
        fix.friction = platformType.getFriction();
        body.createFixture(fix);
//        body.resetMassData();
        PlatformUserData userData = new PlatformUserData(platformType.getWidth(), platformType.getHeight());
        System.out.println("PLATFORMA: (" + body.getPosition().x + ", " + body.getPosition().y + ")");
        LastPlatformY = body.getPosition().y;
        body.setUserData(userData);
        shape.dispose();
        return body;
    }

    public static Body createRunner(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.RUNNER_WIDTH / 2, Constants.RUNNER_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        body.createFixture(shape, Constants.RUNNER_DENSITY);
        body.setFixedRotation(true);
        body.resetMassData();
        body.setUserData(new RunnerUserData(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT));
        shape.dispose();
        return body;
    }

    public static Body createEnemy(World world) {
        EnemyType enemyType = RandomUtils.getRandomEnemyType();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }

    public static float generateRandomShift(){
        Random r = new Random();
        float range = Constants.PLATFORM_RAND_DIFF;
        return -range + (range + range) * r.nextFloat();
//        return r.nextInt((Constants.PLATFORM_RAND_DIFF - -(Constants.PLATFORM_RAND_DIFF))) - Constants.PLATFORM_RAND_DIFF;
    }



}