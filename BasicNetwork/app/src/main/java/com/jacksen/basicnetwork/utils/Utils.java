package com.jacksen.basicnetwork.utils;

import java.io.File;

/**
 * Created by Admin on 2016/7/21.
 */

public class Utils {

    /**
     * @param object
     * @param message
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    /**
     * check file is exist or not.
     *
     * @param file
     * @return
     * @see #checkFileIsExist(String)
     */
    public static boolean checkFileIsExist(File file) {
        if (file.exists() && file.length() > 0) {
            return true;
        }
        return false;
    }


    /**
     * check the file with {@code filePath} is exist or not.
     *
     * @param filePath
     * @return
     * @see #checkFileIsExist(File)
     */
    public static boolean checkFileIsExist(String filePath) {
        return checkFileIsExist(new File(filePath));
    }
}
