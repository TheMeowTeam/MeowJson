package com.github.themeowteam.meowjson;

import java.util.Objects;

public class JsonPrimitive implements JsonElement
{
    private final Object value;

    public JsonPrimitive(short value)
    {
        this.value = value;
    }

    public JsonPrimitive(int value)
    {
        this.value = value;
    }

    public JsonPrimitive(long value)
    {
        this.value = value;
    }

    public JsonPrimitive(float value)
    {
        this.value = value;
    }

    public JsonPrimitive(double value)
    {
        this.value = value;
    }

    public JsonPrimitive(String value)
    {
        this.value = value;
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
