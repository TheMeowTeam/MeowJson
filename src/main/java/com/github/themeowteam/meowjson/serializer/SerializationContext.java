package com.github.themeowteam.meowjson.serializer;

import com.github.themeowteam.meowjson.JsonElement;
import com.github.themeowteam.meowjson.JsonNull;
import com.github.themeowteam.meowjson.JsonPrimitive;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.BiFunction;

/**
 *                )\._.,--....,'``.
 * .b--.        /;   _.. \   _\  (`._ ,.
 * `=,-,-'~~~   `----(,_..'--(,_..'`-.;.'
 *
 * Created by Jérémy L. on 19/09/2018
 */
public class SerializationContext
{
    private final Map<Class, IJsonSerializer> objectSerializers;

    public SerializationContext()
    {
        this.objectSerializers = new HashMap<>();
    }

    public <T> JsonElement serializeObject(Class<T> type, T object) throws JsonSerializationException
    {
        Objects.requireNonNull(type);

        if (object == null)
            return JsonNull.INSTANCE;

        for (Class<?> primitiveAcceptable : JsonPrimitive.ACCEPTABLE)
        {
            if (primitiveAcceptable.isAssignableFrom(type))
            {
                try
                {
                    Constructor<JsonPrimitive> constructor = JsonPrimitive.class.getConstructor(primitiveAcceptable);
                    return constructor.newInstance(object);
                }
                catch (IllegalArgumentException | ReflectiveOperationException ignored)
                {
                    return null;
                }
            }
        }

        IJsonSerializer<T> fallbackSerializer = this.getObjectSerializer(type);

        if (fallbackSerializer != null)
            return fallbackSerializer.serialize(this, object);

        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T deserializeObject(Class<T> type, JsonElement jsonElement) throws JsonSerializationException
    {
        Objects.requireNonNull(type);
        Objects.requireNonNull(jsonElement);

        if (jsonElement instanceof JsonPrimitive)
            return (T) ((JsonPrimitive) jsonElement).getValue();

        IJsonSerializer<T> fallbackSerializer = this.getObjectSerializer(type);

        if (fallbackSerializer != null)
            return fallbackSerializer.deserialize(this, jsonElement);

        return null;
    }

    public <T> void registerObjectSerializer(Class<T> type, IJsonSerializer<T> serializer)
    {
        Objects.requireNonNull(type);
        Objects.requireNonNull(serializer);
        this.objectSerializers.put(type, serializer);
    }

    @SuppressWarnings("unchecked")
    public <T> IJsonSerializer<T> getObjectSerializer(Class<T> typeClass)
    {
        Objects.requireNonNull(typeClass);

        for (Class clazz : this.objectSerializers.keySet())
            if (typeClass.isAssignableFrom(clazz))
                return (IJsonSerializer<T>) this.objectSerializers.get(clazz);

        return new ObjectSerializer<>(typeClass);
    }
}
