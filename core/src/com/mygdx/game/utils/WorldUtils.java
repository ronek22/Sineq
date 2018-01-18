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
import com.mygdx.game.physics.WallUserData;

public class WorldUtils {

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

    public static Body createPlatform(World world) {
        PlatformType platformType = RandomUtils.getRandomPlatformType();
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

    public static Body createLeftWall(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody ;
        bodyDef.position.set(new Vector2(Constants.WALL_LEFT_POSITION,Constants.WALL_POSITION));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.WALL_WIDTH, Constants.WALL_HEIGHT);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.WALL_DENSITY);
        body.resetMassData();
        WallUserData userData = new WallUserData(Constants.WALL_WIDTH,Constants.WALL_HEIGHT);
        body.setUserData(userData);
        shape.dispose();
        return body;
    }

    public static Body createRightWall(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody ;
        bodyDef.position.set(new Vector2(Constants.WALL_RIGHT_POSITION,Constants.WALL_POSITION));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.WALL_WIDTH, Constants.WALL_HEIGHT);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.WALL_DENSITY);
        body.resetMassData();
        WallUserData userData = new WallUserData(Constants.WALL_WIDTH,Constants.WALL_HEIGHT);
        body.setUserData(userData);
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



}