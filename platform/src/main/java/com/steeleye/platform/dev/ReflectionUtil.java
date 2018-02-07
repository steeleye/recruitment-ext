package com.steeleye.platform.dev;

import java.lang.reflect.Field;
import java.util.function.Function;

@SuppressWarnings("unused")
abstract class ReflectionUtil {

    static Class<?> safeClass(Object object) { return applyNullable(object, Object::getClass, Void.class); }

    static boolean isAssignable(Class<?> from, Class<?> to) { return to != null && from.isAssignableFrom(to); }

    @SuppressWarnings("unchecked") static <T> T fieldValue(Object object, Field field) {
        try {
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (Exception e) {
            throw new IllegalStateException("unable to find field value for " + field, e);
        }
    }

    static Field declaredField(Class<?> clazz, String name) {
        Field result = null;
        while (clazz != Object.class) {
            try {
                result = clazz.getDeclaredField(name);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return result;
    }

    static <K, V> V applyNullable(K nullable, Function<K, V> function, V defVal) { return nullable != null ? function.apply(nullable) : defVal; }
}