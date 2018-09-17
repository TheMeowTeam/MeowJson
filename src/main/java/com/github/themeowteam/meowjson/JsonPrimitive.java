package com.github.themeowteam.meowjson;

import java.util.Objects;

public class JsonPrimitive implements JsonElement
{
    private final Object value;
    private final String asText;

    public JsonPrimitive(int value)
    {
        this.value = value;
        this.asText = String.valueOf(value);
    }

    public JsonPrimitive(long value)
    {
        this.value = value;
        this.asText = String.valueOf(value);
    }

    public JsonPrimitive(float value)
    {
        this.value = value;
        this.asText = String.valueOf(value);
    }

    public JsonPrimitive(double value)
    {
        this.value = value;
        this.asText = String.valueOf(value);
    }

    public JsonPrimitive(String value)
    {
        this.value = value;
        this.asText = "\"" + value.replace("\"", "\\\"") + "\"";
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj != null && obj instanceof JsonPrimitive && Objects.equals(this.value, ((JsonPrimitive) obj).value);
    }

    @Override
    public int hashCode()
    {
        return this.value.hashCode();
    }
}
