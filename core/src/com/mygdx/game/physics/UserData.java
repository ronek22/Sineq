package com.mygdx.game.physics;

import com.mygdx.game.enums.UserDataType;

/**
 * Created by kubar on 17.01.2018.
 */

public abstract class UserData {

    protected UserDataType userDataType;
    protected float width;
    protected float height;

    public UserData() {

    }

    public UserData(float width, float height) {
        this.width = width;
        this.height = height;
    }


    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    public UserDataType getUserDataType() {
        return userDataType;
    }
}
