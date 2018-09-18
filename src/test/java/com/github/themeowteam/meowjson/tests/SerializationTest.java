package com.github.themeowteam.meowjson.tests;

import com.github.themeowteam.meowjson.JsonElement;
import com.github.themeowteam.meowjson.JsonObject;
import com.github.themeowteam.meowjson.MeowJson;
import com.github.themeowteam.meowjson.serializer.JsonSerializationException;
import com.github.themeowteam.meowjson.serializer.ObjectSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

/**
 *                )\._.,--....,'``.
 * .b--.        /;   _.. \   _\  (`._ ,.
 * `=,-,-'~~~   `----(,_..'--(,_..'`-.;.'
 *
 * Created by Jérémy L. on 18/09/2018
 */
public class SerializationTest
{
    @Test
    public void testObjectSerialization() throws JsonSerializationException
    {
        MeowJson instance = new MeowJson();
        ObjectSerializer<TestObject> serializer = (ObjectSerializer<TestObject>) instance.getObjectSerializer(TestObject.class);

        TestObject testObject = new TestObject();
        JsonElement serializedObject = serializer.serialize(instance, testObject);

        JsonObject expectedSerializedObject = new JsonObject();
        expectedSerializedObject.putInt("testInt", 4);
        expectedSerializedObject.putBoolean("testBoolean", false);
        expectedSerializedObject.putString("testString", "hello world");

        Assert.assertEquals(serializedObject, expectedSerializedObject);
    }

    private static class TestObject
    {
        public int testInt = 4;
        protected boolean testBoolean = false;
        private String testString = "hello world";

        public TestObject() {}
    }
}
