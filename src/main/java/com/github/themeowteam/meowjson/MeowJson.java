package com.github.themeowteam.meowjson;

import com.github.themeowteam.meowjson.serializer.IJsonSerializer;
import com.github.themeowteam.meowjson.serializer.ObjectSerializer;

import java.util.HashMap;
import java.util.Map;

public class MeowJson
{
    private final Map<Class, IJsonSerializer> objectSerializers;
    private final int serializationDepthLimit = 0;

    public MeowJson()
    {
        this.objectSerializers = new HashMap<>();
    }

    public <T> void registerObjectSerializer(Class<T> type, IJsonSerializer<T> serializer)
    {
        this.objectSerializers.put(type, serializer);
    }

    @SuppressWarnings("unchecked")
    public <T> IJsonSerializer<T> getObjectSerializer(Class<T> typeClass)
    {
        for (Class clazz : this.objectSerializers.keySet())
            if (typeClass.isAssignableFrom(clazz))
                return this.objectSerializers.get(clazz);

        return new ObjectSerializer<>(typeClass);
    }

    public int getSerializationDepthLimit()
    {
        return this.serializationDepthLimit;
    }
}
