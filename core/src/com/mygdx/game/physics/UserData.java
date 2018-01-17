package com.mygdx.game.physics;

import com.mygdx.game.enums.UserDataType;

/**
 * Created by kubar on 17.01.2018.
 */

public abstract class UserData {
    protected UserDataType userDataType;

    public UserData() {

    }

    public UserDataType getUserDataType() {
        return userDataType;
    }
}
