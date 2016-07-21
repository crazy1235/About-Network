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
import java.util.List;
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
     * @param is
     * @return
     * @throws IOException
     */
    public static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
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
