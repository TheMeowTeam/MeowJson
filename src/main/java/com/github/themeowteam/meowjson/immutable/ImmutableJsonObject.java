package com.github.themeowteam.meowjson.immutable;

import com.github.themeowteam.meowjson.JsonElement;
import com.github.themeowteam.meowjson.JsonObject;
import com.github.themeowteam.meowjson.JsonPrimitive;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ImmutableJsonObject extends JsonObject
{
    private final Map<JsonPrimitive, JsonElement> immutableElements;

    public ImmutableJsonObject(Map<JsonPrimitive, JsonElement> immutableElements)
    {
        this.immutableElements = immutableElements;
    }

    @Override
    protected Map<JsonPrimitive, JsonElement> createValueMap()
    {
        return Collections.unmodifiableMap(this.immutableElements);
    }
}
