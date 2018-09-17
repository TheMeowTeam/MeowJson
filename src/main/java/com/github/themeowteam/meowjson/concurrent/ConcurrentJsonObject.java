package com.github.themeowteam.meowjson.concurrent;

import com.github.themeowteam.meowjson.JsonElement;
import com.github.themeowteam.meowjson.JsonObject;
import com.github.themeowteam.meowjson.JsonPrimitive;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentJsonObject extends JsonObject
{
    @Override
    protected Map<JsonPrimitive, JsonElement> createValueMap()
    {
        return new ConcurrentHashMap<>();
    }
}
