package com.mygdx.game.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.enums.UserDataType;
import com.mygdx.game.physics.UserData;

/**
 * Created by kubar on 17.01.2018.
 */

public class BodyUtils {

    public static boolean bodyInBounds(Body body) {
        UserData userData = (UserData) body.getUserData();

        switch (userData.getUserDataType()) {
            case RUNNER:
            case ENEMY:
                return body.getPosition().x + userData.getWidth() / 2 > 0;
            case FALLING_ROCK:
                return body.getPosition().x + userData.getWidth() / 2 > 0;
            case PLATFORM:
                return body.getPosition().x + userData.getWidth() / 2 > 0;
            case BULLET:
                return body.getPosition().x + userData.getWidth() / 2 < 20;
            case SPIKE_GROUND:
                return body.getPosition().x + userData.getWidth() / 2 > 0;
        }

        return true;
    }



    public static boolean bodyIsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }
    public static boolean bodyIsEnemy(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.ENEMY;
    }
    public static boolean bodyIsRock(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.FALLING_ROCK;
    }

    public static boolean bodyIsPlatform(Body body){
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.PLATFORM;
    }

    public static boolean bodyIsBullet(Body body){
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.BULLET;
    }

    public static boolean bodyIsSpikes(Body body){
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.SPIKE_GROUND;
    }

}
