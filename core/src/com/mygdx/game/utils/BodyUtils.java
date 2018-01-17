package com.mygdx.game.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.enums.UserDataType;
import com.mygdx.game.physics.UserData;

/**
 * Created by kubar on 17.01.2018.
 */

public class BodyUtils {
    public static boolean bodyIsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }
}
