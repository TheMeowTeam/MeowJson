package com.github.themeowteam.meowjson;

public interface JsonElement
{
    String asString();

    default JsonObject asJsonObject()
    {
        return (JsonObject) this;
    }

    default JsonArray asJsonArray()
    {
        return (JsonArray) this;
    }

    default JsonPrimitive asJsonPrimitive()
    {
        return (JsonPrimitive) this;
    }
}
