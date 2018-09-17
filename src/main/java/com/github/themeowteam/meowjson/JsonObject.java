package com.github.themeowteam.meowjson;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonObject implements JsonElement
{
    private final Map<JsonPrimitive, JsonElement> values;

    public JsonObject()
    {
        this.values = this.createValueMap();
    }

    protected Map<JsonPrimitive, JsonElement> createValueMap()
    {
        return new HashMap<>();
    }

    public void setInt(String key, int value)
    {
        Objects.requireNonNull(key);
        this.values.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void setLong(String key, long value)
    {
        Objects.requireNonNull(key);
        this.values.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void setFloat(String key, float value)
    {
        Objects.requireNonNull(key);
        this.values.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void setDouble(String key, double value)
    {
        Objects.requireNonNull(key);
        this.values.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void setString(String key, String value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        this.values.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void setObject(String key, JsonObject value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        this.values.put(new JsonPrimitive(key), value);
    }

    public void setArray(String key, JsonArray value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        this.values.put(new JsonPrimitive(key), value);
    }

    public void setNull(String key)
    {
        Objects.requireNonNull(key);
        this.values.put(new JsonPrimitive(key), JsonNull.INSTANCE);
    }

    public JsonElement remove(String key)
    {
        Objects.requireNonNull(key);
        return this.values.remove(new JsonPrimitive(key));
    }
}
