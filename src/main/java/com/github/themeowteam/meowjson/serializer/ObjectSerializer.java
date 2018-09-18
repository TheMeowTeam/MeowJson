package com.github.themeowteam.meowjson.serializer;

import com.github.themeowteam.meowjson.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 *                )\._.,--....,'``.
 * .b--.        /;   _.. \   _\  (`._ ,.
 * `=,-,-'~~~   `----(,_..'--(,_..'`-.;.'
 *
 * Created by Jérémy L. on 18/09/2018
 */
public class ObjectSerializer<T> implements IJsonSerializer<T>
{
    private static final Class[] PRIMITIVE_ACCEPTABLE = {
            short.class, int.class, long.class, float.class, double.class, boolean.class,
            Short.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class, String.class
    };

    private final Class<T> assignableClass;

    public ObjectSerializer(Class<T> assignableClass)
    {
        this.assignableClass = assignableClass;
    }

    @Override
    public JsonElement serialize(MeowJson instance, T object) throws JsonSerializationException
    {
        JsonObject jsonObject = new JsonObject();

        try
        {
            for (Field field : this.assignableClass.getDeclaredFields())
            {
                String fieldName = field.getName();

                if (field.isAnnotationPresent(INamedJsonField.class))
                    fieldName = field.getAnnotation(INamedJsonField.class).name();

                field.setAccessible(true);

                Class<?> fieldClass = field.getType();
                Object fieldValue = field.get(object);
                JsonElement serializedElement = serializeByType(instance, fieldClass, fieldValue);

                if (serializedElement == null)
                {
                    throw new JsonSerializationException(String.format(
                            "No serializer acceptable to serialize the field '%s' of type '%s'",
                            field.getName(), fieldClass.getSimpleName()
                    ));
                }

                jsonObject.put(fieldName, serializedElement);
            }
        }
        catch (ReflectiveOperationException e)
        {
            throw new JsonSerializationException(e.getMessage());
        }

        return jsonObject;
    }

    @Override
    public T deserialize(MeowJson instance, JsonElement jsonElement) throws JsonSerializationException
    {
        if (!(jsonElement instanceof JsonObject))
            throw new JsonSerializationException("Can't deserialize from an element which is not a JSON object");

        try
        {
            T object = this.assignableClass.getDeclaredConstructor().newInstance();

            for (Field field : object.getClass().getDeclaredFields())
            {
                String fieldName = field.getName();

                if (field.isAnnotationPresent(INamedJsonField.class))
                    fieldName = field.getAnnotation(INamedJsonField.class).name();

                if (((JsonObject) jsonElement).containsKey(fieldName))
                {
                    Class<?> fieldClass = field.getDeclaringClass();
                    JsonElement fieldJsonElement = ((JsonObject) jsonElement).get(fieldName);
                    Object futureFieldValue = deserializeByType(instance, fieldClass, fieldJsonElement);

                    if (futureFieldValue == null)
                    {
                        throw new JsonSerializationException(String.format(
                                "No serializer acceptable to serialize the field '%s' of type '%s'",
                                field.getName(), fieldClass.getSimpleName()
                        ));
                    }

                    field.setAccessible(true);
                    field.set(object, futureFieldValue);
                }
            }

            return object;
        }
        catch (ReflectiveOperationException e)
        {
            throw new JsonSerializationException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static JsonElement serializeByType(MeowJson instance, Class<?> type, Object object)
            throws JsonSerializationException
    {
        if (object == null)
            return JsonNull.INSTANCE;

        for (Class<?> primitiveAcceptable : PRIMITIVE_ACCEPTABLE)
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

        IJsonSerializer fallbackSerializer = instance.getObjectSerializer(type);

        if (fallbackSerializer != null)
            return fallbackSerializer.serialize(instance, object);

        return null;
    }

    private static Object deserializeByType(MeowJson instance, Class<?> type, JsonElement jsonElement)
            throws JsonSerializationException
    {
        if (jsonElement instanceof JsonPrimitive)
            return ((JsonPrimitive) jsonElement).getValue();

        IJsonSerializer fallbackSerializer = instance.getObjectSerializer(type);

        if (fallbackSerializer != null)
            return fallbackSerializer.deserialize(instance, jsonElement);

        return null;
    }
}
