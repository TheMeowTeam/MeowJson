package com.github.themeowteam.meowjson.model;

import com.github.themeowteam.meowjson.JsonElement;

/**
 *                )\._.,--....,'``.
 * .b--.        /;   _.. \   _\  (`._ ,.
 * `=,-,-'~~~   `----(,_..'--(,_..'`-.;.'
 *
 * Created by Jérémy L. on 20/09/2018
 */
public interface IJsonModelElement
{
    boolean validate(JsonElement jsonElement);
}
