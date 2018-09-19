package com.github.themeowteam.meowjson;

import com.github.themeowteam.meowjson.serializer.SerializationContext;

public class MeowJson
{
    public static final MeowJson INSTANCE;

    private final SerializationContext serializationContext;

    private MeowJson()
    {
        this.serializationContext = new SerializationContext();
    }

    public SerializationContext getSerializationContext()
    {
        return this.serializationContext;
    }

    public static class Builder
    {
        public MeowJson build()
        {
            return new MeowJson();
        }
    }

    static
    {
        INSTANCE = new Builder().build();
    }
}
