package com.eventvisualizer.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceRegistry {

    private static final Map<Class<?>, Object> services = new ConcurrentHashMap<>();

    private ServiceRegistry() {}

    public static <T> void register(Class<T> clazz, T service) {
        services.put(clazz, service);
    }

    public static <T> T get(Class<T> clazz) {
        return clazz.cast(services.get(clazz));
    }
}