package com.steeleye.platform.dev;

import com.google.common.collect.ImmutableMap;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableList.of;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class DeepRetrieverTest extends DeepRetriever {
    public static Object[][] mapInputs() {
        return new Object[][]{
            {mapWithVal(), "value", String.class, of("foo")},
            {mapWithArr(), "value", String.class, of("foo", "bar")},
            {mapWithMap(), "value", Map.class, of(ImmutableMap.of("key", "foo"))},
            {mapWithMapArr(), "value", Map.class, of(ImmutableMap.of("key", "foo"), ImmutableMap.of("key", "bar"))},
            {mapWithNullEmpties(), "value.key", String.class, of("")},
            {mapWithMap(), "value.key", String.class, of("foo")},
            {mapWithMapArr(), "value.key", String.class, of("foo", "bar")},
            {mapWithMulti(), "value.keys.key", String.class, of("foo", "bar")},
        };
    }

    @Test @Parameters(method = "mapInputs")
    public <T> void deepCollectSuccess(Map<String, Object> map, String prop, Class<T> expClass, List<T> expected) {
        assertThat(deepCollect(map, prop, expClass), is(expected));
    }

    @Test public void deepCollectSingle() {
        Map<String, Object> src = ImmutableMap.of("identifiers", ImmutableMap.of("foo", "ali@foo.net", "bars", of("kate@foo.net")));
        assertThat(deepCollect(src, "identifiers.foo", String.class), is(of("ali@foo.net")));
        assertThat(deepCollect(src, "identifiers.bazs", String.class), is(of()));
        assertThat(deepCollect(src, "identifiers.bars", String.class), is(of("kate@foo.net")));
    }

    private static Map<String, Object> mapWithVal() { return ImmutableMap.of("value", "foo"); }

    private static Map<String, Object> mapWithArr() { return ImmutableMap.of("value", newArrayList("foo", "bar")); }

    private static Map<String, Object> mapWithNullEmpties() { return ImmutableMap.of("value", ImmutableMap.of("key", newArrayList(null, ""))); }

    private static Map<String, Object> mapWithMap() { return ImmutableMap.of("value", ImmutableMap.of("key", "foo")); }

    private static Map<String, Object> mapWithMapArr() { return ImmutableMap.of("value", of(ImmutableMap.of("key", "foo"), ImmutableMap.of("key", "bar"))); }

    private static Map<String, Object> mapWithMulti() {
        return ImmutableMap.of("value", of(ImmutableMap.of("keys", of(ImmutableMap.of("key", "foo"))),
            ImmutableMap.of("keys", of(ImmutableMap.of("key", "bar")))));
    }
}