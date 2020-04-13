package net.kemitix.naolo.api;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.hamcrest.CustomMatcher;

class JsonMatcher<T> extends CustomMatcher<T> {

    private final JsonObject expectedObject;
    private final JsonArray expectedArray;
    private final boolean isObject;

    public JsonMatcher(
            final JsonObject expected
    ) {
        super(expected.encode());
        isObject = true;
        expectedObject = expected;
        expectedArray = null;
    }

    public JsonMatcher(
            final JsonArray expected
    ) {
        super(expected.encode());
        isObject = false;
        expectedObject = null;
        expectedArray = expected;
    }

    public static JsonMatcher<Object> jsonArray(final JsonArray array) {
        return new JsonMatcher<>(array);
    }

    public static JsonMatcher<Object> jsonObject(final JsonObject object) {
        return new JsonMatcher<>(object);
    }

    @Override
    public boolean matches(final Object actual) {
        return Json.decodeValue((String) actual)
                .equals(isObject
                        ? expectedObject
                        : expectedArray);
    }
}
