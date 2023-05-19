package com.trading.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;


public class LRUCacheTest {

    LRUCache<Integer> cache;

    @BeforeEach
    public void setup() {
        cache = new LRUCache<>();
    }

    @Test
    public void testSetCapacity() {
        cache.setCapacity(3);
        Assertions.assertEquals(3, cache.getCapacity());
    }

    @Test
    public void testCapacity() {
        cache.setCapacity(3);

        for (int i = 0; i < 4; i++) cache.cache(String.valueOf(i), i);

        Optional<Integer> zero = cache.get("0");
        Assertions.assertFalse(zero.isPresent(), "Zero should have been evicted due to cache reaching capacity.");

        Optional<Integer> one = cache.get("1");

        Optional<Integer> two = cache.get("2");
        Optional<Integer> three = cache.get("3");

    }


}
