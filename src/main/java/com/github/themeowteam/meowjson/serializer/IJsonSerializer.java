package com.github.themeowteam.meowjson.serializer;

import com.github.themeowteam.meowjson.JsonElement;

/**
 *                )\._.,--....,'``.
 * .b--.        /;   _.. \   _\  (`._ ,.
 * `=,-,-'~~~   `----(,_..'--(,_..'`-.;.'
 *
 * Created by Jérémy L. on 18/09/2018
 */
public interface IJsonSerializer<T>
{
    JsonElement serialize(SerializationContext context, T object) throws JsonSerializationException;
    T deserialize(SerializationContext context, JsonElement jsonElement) throws JsonSerializationException;
}
