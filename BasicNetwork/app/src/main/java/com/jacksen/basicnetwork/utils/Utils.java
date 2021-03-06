package com.jacksen.basicnetwork.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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

    /**
     * InputStream to StringBuilder
     *
     * @param inputStream
     * @return
     */
    public static StringBuilder convertStreamToStringBuilder(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    /**
     * InputStream to String
     *
     * @param inputStream
     * @return
     */
    public static String convertStreamToString(InputStream inputStream) {
        String line = "";
        String result = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @param outputStream
     * @param paramsList
     */
    public static void writeParams(OutputStream outputStream, HashMap<String, String> paramsList) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> keys = paramsList.keySet();
        Iterator<String> iterable = keys.iterator();
        while (iterable.hasNext()) {
            if (!TextUtils.isEmpty(stringBuilder)) {
                stringBuilder.append("&");
            }
            try {
                String key = iterable.next();
                stringBuilder.append(URLEncoder.encode(key, "UTF-8"));
                stringBuilder.append(URLEncoder.encode(paramsList.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(stringBuilder.toString());
            writer.flush();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
