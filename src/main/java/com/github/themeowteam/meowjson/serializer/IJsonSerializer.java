package com.github.themeowteam.meowjson.serializer;

import com.github.themeowteam.meowjson.JsonElement;
import com.github.themeowteam.meowjson.MeowJson;

/**
 *                )\._.,--....,'``.
 * .b--.        /;   _.. \   _\  (`._ ,.
 * `=,-,-'~~~   `----(,_..'--(,_..'`-.;.'
 *
 * Created by Jérémy L. on 18/09/2018
 */
public interface IJsonSerializer<T>
{
    JsonElement serialize(MeowJson instance, T object) throws JsonSerializationException;
    T deserialize(MeowJson instance, JsonElement jsonElement) throws JsonSerializationException;
}
