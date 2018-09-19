package com.github.themeowteam.meowjson.serializer;

import com.github.themeowteam.meowjson.*;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 *                )\._.,--....,'``.
 * .b--.        /;   _.. \   _\  (`._ ,.
 * `=,-,-'~~~   `----(,_..'--(,_..'`-.;.'
 *
 * Created by Jérémy L. on 18/09/2018
 */
public class ObjectSerializer<T> implements IJsonSerializer<T>
{
    private final Class<T> assignableClass;

    public ObjectSerializer(Class<T> assignableClass)
    {
        this.assignableClass = assignableClass;
    }

    @Override
    public JsonElement serialize(SerializationContext context, T object) throws JsonSerializationException
    {
        return this.serialize0(context, object);
    }

    @SuppressWarnings("unchecked")
    private <A> JsonElement serialize0(SerializationContext context, T object) throws JsonSerializationException
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

                Class<A> fieldClass = (Class<A>) field.getType();
                A fieldValue = (A) field.get(object);
                JsonElement serializedElement = null;

                try
                {
                    serializedElement = context.serializeObject(fieldClass, fieldValue);
                }
                catch (JsonSerializationException ignored) {}

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
    public T deserialize(SerializationContext context, JsonElement jsonElement) throws JsonSerializationException
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
                    Class<?> fieldClass = field.getType();
                    JsonElement fieldJsonElement = ((JsonObject) jsonElement).get(fieldName);
                    Object futureFieldValue = null;

                    try
                    {
                        futureFieldValue = context.deserializeObject(fieldClass, fieldJsonElement);
                    }
                    catch (JsonSerializationException ignored) {}

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
}
