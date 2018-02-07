package com.steeleye.platform.dev;

import java.util.LinkedHashMap;
import java.util.Map;

final class MapBuilder<K, V> {
    private final Map<K, V> builder = new LinkedHashMap<>();

    static <K, V> Map<K, V> mapOf(K k1, V v1) { return new MapBuilder<K, V>().put(k1, v1).build(); }

    static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return new MapBuilder<K, V>().put(k1, v1).put(k2, v2).put(k3, v3).put(k4, v4).build();
    }

    private MapBuilder<K, V> put(K key, V value) { if (value != null) builder.put(key, value); return this; }

    private Map<K, V> build() { return builder; }
}