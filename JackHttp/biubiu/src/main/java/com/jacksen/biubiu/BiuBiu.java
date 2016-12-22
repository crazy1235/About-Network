package com.jacksen.biubiu;

/**
 * Created by jacksen on 2016/7/29.
 */
public class BiuBiu {

    private static BiuBiu ourInstance = new BiuBiu();

    public static BiuBiu getInstance() {
        return ourInstance;
    }

    private BiuBiu() {
    }
}
