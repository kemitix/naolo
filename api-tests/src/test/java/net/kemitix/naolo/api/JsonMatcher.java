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

    public static JsonMatcher<Object> jsonArray(final JsonArray vetList) {
        return new JsonMatcher<>(vetList);
    }

    public static JsonMatcher<Object> jsonObject(final JsonObject addedVet) {
        return new JsonMatcher<>(addedVet);
    }

    @Override
    public boolean matches(final Object actual) {
        return Json.decodeValue((String) actual)
                .equals(isObject
                        ? expectedObject
                        : expectedArray);
    }
}
