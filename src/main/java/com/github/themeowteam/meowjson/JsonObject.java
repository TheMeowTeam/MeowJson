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
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return this.values.put(key, value);
    }

    public JsonElement put(String key, JsonElement value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return this.put(new JsonPrimitive(key), value);
    }

    public JsonElement putShort(String key, short value)
    {
        Objects.requireNonNull(key);
        return this.put(key, new JsonPrimitive(value));
    }

    public JsonElement putInt(String key, int value)
    {
        Objects.requireNonNull(key);
        return this.put(key, new JsonPrimitive(value));
    }

    public JsonElement putLong(String key, long value)
    {
        Objects.requireNonNull(key);
        return this.put(key, new JsonPrimitive(value));
    }

    public JsonElement putFloat(String key, float value)
    {
        Objects.requireNonNull(key);
        return this.put(key, new JsonPrimitive(value));
    }

    public JsonElement putDouble(String key, double value)
    {
        Objects.requireNonNull(key);
        return this.put(key, new JsonPrimitive(value));
    }

    public JsonElement putBoolean(String key, boolean value)
    {
        Objects.requireNonNull(key);
        return this.put(key, new JsonPrimitive(value));
    }

    public JsonElement putString(String key, String value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return this.put(key, new JsonPrimitive(value));
    }

    public JsonElement putJsonObject(String key, JsonObject value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return this.put(key, value);
    }

    public JsonElement putJsonArray(String key, JsonArray value)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return this.put(key, value);
    }

    public JsonElement putNull(String key)
    {
        Objects.requireNonNull(key);
        return this.put(key, JsonNull.INSTANCE);
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

    public boolean containsKey(String key)
    {
        return this.containsKey(new JsonPrimitive(key));
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

    public JsonElement get(String key)
    {
        return this.get(new JsonPrimitive(key));
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

    @Override
    public String asString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        Iterator<Entry<JsonPrimitive, JsonElement>> entryIterator = this.entrySet().iterator();

        while (entryIterator.hasNext())
        {
            Entry<JsonPrimitive, JsonElement> entry = entryIterator.next();

            stringBuilder.append(entry.getKey().asString()).append(":").append(entry.getValue().asString());

            if (entryIterator.hasNext())
                stringBuilder.append(",");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    protected Map<JsonPrimitive, JsonElement> createValueMap()
    {
        return new HashMap<>();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof JsonObject))
            return false;

        JsonObject jsonObj = (JsonObject) obj;

        if (jsonObj.size() != this.size())
            return false;

        for (JsonPrimitive key : this.keySet())
        {
            if (!jsonObj.containsKey(key))
                return false;
            else if (!Objects.equals(jsonObj.get(key), this.get(key)))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return this.values.hashCode();
    }
}
