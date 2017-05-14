/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley;

import java.util.Collections;
import java.util.Map;

/**
 * An interface for a cache keyed by a String with a byte array as data.
 */
public interface Cache {
    /**
     * 通过key得到缓存的实体对象
     */
    public Entry get(String key);

    /**
     * 保存一个请求的实体对象
     */
    public void put(String key, Entry entry);

    /**
     * 初始化
     */
    public void initialize();

    /**
     * Invalidates an entry in the cache.
     *
     * @param key        Cache key
     * @param fullExpire True to fully expire the entry, false to soft expire
     */
    public void invalidate(String key, boolean fullExpire);

    /**
     * 根据key移除一个缓存实体
     */
    public void remove(String key);

    /**
     * 清除缓存
     */
    public void clear();

    /**
     * Data and metadata for an entry returned by the cache.
     */
    public static class Entry {
        /**
         * The data returned from cache.
         */
        public byte[] data;

        /**
         * ETag for cache coherency.
         */
        public String etag;

        /**
         * Date of this response as reported by the server.
         */
        public long serverDate;

        /**
         * TTL for this record.
         */
        public long ttl;

        /**
         * Soft TTL for this record.
         */
        public long softTtl;

        /**
         * Immutable response headers as received from server; must be non-null.
         */
        public Map<String, String> responseHeaders = Collections.emptyMap();

        /**
         * True if the entry is expired.
         */
        // 判断是否到期
        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }

        /**
         * True if a refresh is needed from the original data source.
         */
        // 判断是否需要刷新数据
        public boolean refreshNeeded() {
            return this.softTtl < System.currentTimeMillis();
        }
    }

}
