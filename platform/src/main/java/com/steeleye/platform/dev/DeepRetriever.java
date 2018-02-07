package com.steeleye.platform.dev;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static com.steeleye.platform.dev.ReflectionUtil.declaredField;
import static com.steeleye.platform.dev.ReflectionUtil.fieldValue;

@SuppressWarnings({"unchecked", "WeakerAccess"})
public abstract class DeepRetriever {
    public static final Class<Map<String, Object>> MAP_CLASS = (Class<Map<String, Object>>) new TypeToken<Map<String, Object>>() {}.getRawType();
    protected static Pattern LIST_PATTERN = Pattern.compile("\\[(\\d+)\\]");

    public static <T> T deepRetrieve(Object map, String property) {
        checkArgument(!property.startsWith("."), "property cannot begin with a dot");
        if (!property.contains(".")) return mapGetOrReflect(map, property);

        String[] parts = property.split("\\.");
        Object child = map;
        for (int i = 0; i < parts.length - 1; i++) {
            child = mapGetOrReflect(child, parts[i]);
            if (child == null) return null;
        }
        return (T) mapGetOrReflect(child, parts[parts.length - 1]);
    }

    public static <T> T mapGetOrReflect(Object object, String property) {
        if (object instanceof List) {
            Matcher matcher = LIST_PATTERN.matcher(property);
            checkArgument(matcher.matches(), "property `" + property + "` not formatted as a [index]");
            return (T) ((List) object).get(Integer.parseInt(matcher.group(1)));
        }
        if (object instanceof Map) return (T) ((Map) object).get(property);
        Field field = declaredField(object.getClass(), property);
        return field == null ? null : (T) fieldValue(object, field);
    }

    public static <T> List<T> deepCollect(Map<String, Object> map, String property, Class<? super T> clazz) {
        checkArgument(!property.startsWith("."), "property cannot begin with a dot");
        if (!property.contains(".")) {
            Object val = map.get(property);
            return val == null ? ImmutableList.of() : (val instanceof List ? (List<T>) val : ImmutableList.of((T) val));
        }
        //TODO IMPLEMENT THE PORTION OF THIS METHOD
        return ImmutableList.of();
    }
}