package com.github.themeowteam.meowjson;

public class JsonNull implements JsonElement
{
    public static final JsonNull INSTANCE;

    static
    {
        INSTANCE = new JsonNull();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj != null && obj instanceof JsonNull;
    }
}
