package com.github.themeowteam.meowjson;

public class JsonNull implements JsonElement
{
    public static final JsonNull INSTANCE;

    static
    {
        INSTANCE = new JsonNull();
    }

    @Override
    public String asString()
    {
        return "null";
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof JsonNull;
    }
}
