package com.github.themeowteam.meowjson.concurrent;

import com.github.themeowteam.meowjson.JsonArray;
import com.github.themeowteam.meowjson.JsonElement;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ConcurrentJsonArray extends JsonArray
{
    @Override
    protected List<JsonElement> createValueList()
    {
        return Collections.synchronizedList(new LinkedList<>());
    }
}
