package com.github.themeowteam.meowjson.serializer;

import com.github.themeowteam.meowjson.JsonElement;

/**
 *                )\._.,--....,'``.
 * .b--.        /;   _.. \   _\  (`._ ,.
 * `=,-,-'~~~   `----(,_..'--(,_..'`-.;.'
 *
 * Created by Jérémy L. on 18/09/2018
 */
public interface IJsonDeserializer<T>
{
    T deserialize(JsonElement jsonElement);
}
