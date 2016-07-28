package com.jacksen.jackhttp;

import android.util.LruCache;

/**
 * 内存缓存
 * Created by Admin on 2016/7/28.
 */

public class LruMemoryCache implements Cache<String, Response> {

    private LruCache<String, Response> responseLruCache;

    public LruMemoryCache() {
        // 最大可用内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // 1/8作为缓存
        int cacheSize = maxMemory / 8;

        responseLruCache = new LruCache<String, Response>(cacheSize) {
            @Override
            protected int sizeOf(String key, Response value) {
                return value.getRawData().length / 1024;
            }
        };
    }

    @Override
    public Response get(String key) {
        return responseLruCache.get(key);
    }

    @Override
    public void put(String key, Response value) {
        responseLruCache.put(key, value);
    }

    @Override
    public void remove(String key) {
        responseLruCache.remove(key);
    }
}
