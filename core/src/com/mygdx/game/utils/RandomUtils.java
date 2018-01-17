package com.mygdx.game.utils;

import com.mygdx.game.actors.Platform;
import com.mygdx.game.enums.EnemyType;
import com.mygdx.game.enums.PlatformType;

import java.util.Random;

public class RandomUtils {

    public static EnemyType getRandomEnemyType() {
        RandomEnum<EnemyType> randomEnum = new RandomEnum<EnemyType>(EnemyType.class);
        return randomEnum.random();
    }

    public static PlatformType getRandomPlatformType() {
        RandomEnum<PlatformType> randomEnum = new RandomEnum<PlatformType>(PlatformType.class);
        return randomEnum.random();
    }


    private static class RandomEnum<E extends Enum> {

        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }

}