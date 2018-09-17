package com.github.themeowteam.meowjson;

import java.util.LinkedList;
import java.util.List;

public class JsonArray implements JsonElement
{
    private final List<JsonElement> values;

    public JsonArray()
    {
        this.values = this.createValueList();
    }

    protected List<JsonElement> createValueList()
    {
        return new LinkedList<>();
    }

    public void addInt(int value)
    {
        this.values.add(new JsonPrimitive(value));
    }

    public void addLong(long value)
    {
        this.values.add(new JsonPrimitive(value));
    }

    public void addFloat(float value)
    {
        this.values.add(new JsonPrimitive(value));
    }

    public void addDouble(double value)
    {
        this.values.add(new JsonPrimitive(value));
    }

    public void addString(String value)
    {
        this.values.add(new JsonPrimitive(value));
    }

    public void addNull()
    {
        this.values.add(JsonNull.INSTANCE);
    }

    public void addInt(int index, int value)
    {
        this.values.add(index, new JsonPrimitive(value));
    }

    public void addLong(int index, long value)
    {
        this.values.add(index, new JsonPrimitive(value));
    }

    public void addFloat(int index, float value)
    {
        this.values.add(index, new JsonPrimitive(value));
    }

    public void addDouble(int index, double value)
    {
        this.values.add(index, new JsonPrimitive(value));
    }

    public void addString(int index, String value)
    {
        this.values.add(index, new JsonPrimitive(value));
    }

    public void addNull(int index)
    {
        this.values.add(index, JsonNull.INSTANCE);
    }

    public void addAll(JsonArray values)
    {
        this.values.addAll(values.values);
    }

    public void addAll(int index, JsonArray values)
    {
        this.values.addAll(index, values.values);
    }

    public JsonElement remove(int index)
    {
        return this.values.remove(index);
    }
}
