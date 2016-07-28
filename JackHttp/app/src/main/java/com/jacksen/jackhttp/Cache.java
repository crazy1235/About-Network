package com.jacksen.jackhttp;

/**
 * Created by Admin on 2016/7/28.
 */

public interface Cache<K, V> {

    public V get(K key);

    public void put(K key, V value);

    public void remove(K key);
}
