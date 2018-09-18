package com.github.themeowteam.meowjson;

import java.util.*;

public class JsonObject implements JsonElement, Map<JsonPrimitive, JsonElement>
{
    private final Map<JsonPrimitive, JsonElement> values;

    public JsonObject()
    {
        this.values = this.createValueMap();
    }

    @Override
    public JsonElement put(JsonPrimitive key, JsonElement value)
    {
        return this.values.put(key, value);
    }

    public void putShort(String key, short value)
    {
        Objects.requireNonNull(key);
        this.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void putInt(String key, int value)
    {
        Objects.requireNonNull(key);
        this.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void putLong(String key, long value)
    {
        Objects.requireNonNull(key);
        this.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void putFloat(String key, float value)
    {
        Objects.requireNonNull(key);
        this.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void putDouble(String key, double value)
    {
        Objects.requireNonNull(key);
        this.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void putString(String key, String value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        this.put(new JsonPrimitive(key), new JsonPrimitive(value));
    }

    public void putJsonObject(String key, JsonObject value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        this.put(new JsonPrimitive(key), value);
    }

    public void putJsonArray(String key, JsonArray value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        this.put(new JsonPrimitive(key), value);
    }

    public void putNull(String key)
    {
        Objects.requireNonNull(key);
        this.put(new JsonPrimitive(key), JsonNull.INSTANCE);
    }

    @Override
    public void putAll(Map<? extends JsonPrimitive, ? extends JsonElement> m)
    {
        this.values.putAll(m);
    }

    @Override
    public JsonElement remove(Object key)
    {
        Objects.requireNonNull(key);
        return this.values.remove(key);
    }

    public JsonElement remove(String key)
    {
        Objects.requireNonNull(key);
        return this.remove(new JsonPrimitive(key));
    }

    @Override
    public boolean containsKey(Object key)
    {
        return this.values.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return this.values.containsValue(value);
    }

    @Override
    public JsonElement get(Object key)
    {
        return this.values.get(key);
    }

    @Override
    public void clear()
    {
        this.values.clear();
    }

    @Override
    public Set<JsonPrimitive> keySet()
    {
        return this.values.keySet();
    }

    @Override
    public Collection<JsonElement> values()
    {
        return this.values.values();
    }

    @Override
    public Set<Entry<JsonPrimitive, JsonElement>> entrySet()
    {
        return this.values.entrySet();
    }

    @Override
    public int size()
    {
        return this.values.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.values.isEmpty();
    }

    protected Map<JsonPrimitive, JsonElement> createValueMap()
    {
        return new HashMap<>();
    }
}
