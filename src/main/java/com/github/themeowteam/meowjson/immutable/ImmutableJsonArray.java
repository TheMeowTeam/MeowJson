package com.github.themeowteam.meowjson.immutable;

import com.github.themeowteam.meowjson.JsonArray;
import com.github.themeowteam.meowjson.JsonElement;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ImmutableJsonArray extends JsonArray
{
    private final List<JsonElement> immutableElements;

    public ImmutableJsonArray(List<JsonElement> immutableElements)
    {
        this.immutableElements = immutableElements;
    }

    @Override
    protected List<JsonElement> createValueList()
    {
        return Collections.unmodifiableList(this.immutableElements);
    }
}
