package com.trading.util;

import java.util.*;

public class LRUCache<T> {

    private int capacity;
    private Hashtable<String, T> store;
    private Queue<String> cache;

    public LRUCache() {
        this(256);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.store = new Hashtable<>();
        this.cache = new LinkedList<>();
    }

    public void cache(String id, T val) {
        if (cache.contains(id)) evict(id);
        while (cache.size() >= this.capacity) evictFirst();

        cache.offer(id);
        store.put(id, val);
    }

    public Optional<T> get(String id) {
        T val = store.get(id);
        if (Objects.isNull(val)) return Optional.empty();
        cache(id, val);
        return Optional.of(val);
    }

    public void evict(String id) {
        cache.remove(id);
        store.remove(id);
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        while (cache.size() > this.capacity) evictFirst();
    }

    private void evictFirst() {
        evict(cache.peek());
    }

}
