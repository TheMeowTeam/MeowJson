package com.github.themeowteam.meowjson;

import java.util.Objects;

public class JsonPrimitive implements JsonElement
{
    public static final Class[] ACCEPTABLE = {
            short.class, int.class, long.class, float.class, double.class, boolean.class,
            Short.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class, String.class
    };

    private final Object value;

    public JsonPrimitive(short value)
    {
        this.value = value;
    }

    public JsonPrimitive(Short value)
    {
        this.value = value;
    }

    public JsonPrimitive(int value)
    {
        this.value = value;
    }

    public JsonPrimitive(Integer value)
    {
        this.value = value;
    }

    public JsonPrimitive(long value)
    {
        this.value = value;
    }

    public JsonPrimitive(Long value)
    {
        this.value = value;
    }

    public JsonPrimitive(float value)
    {
        this.value = value;
    }

    public JsonPrimitive(Float value)
    {
        this.value = value;
    }

    public JsonPrimitive(double value)
    {
        this.value = value;
    }

    public JsonPrimitive(Double value)
    {
        this.value = value;
    }

    public JsonPrimitive(boolean value)
    {
        this.value = value;
    }

    public JsonPrimitive(Boolean value)
    {
        this.value = value;
    }

    public JsonPrimitive(String value)
    {
        this.value = value;
    }

    public Object getValue()
    {
        return this.value;
    }

    @Override
    public String asString()
    {
        if (this.value instanceof String)
            return "\"" + ((String) this.value).replace("\"", "\\\"") + "\"";
        else
            return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof JsonPrimitive && Objects.equals(this.value, ((JsonPrimitive) obj).value);
    }

    @Override
    public int hashCode()
    {
        return this.value.hashCode();
    }
}
