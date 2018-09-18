package com.github.themeowteam.meowjson.tests;

import com.github.themeowteam.meowjson.JsonElement;
import com.github.themeowteam.meowjson.JsonObject;
import com.github.themeowteam.meowjson.MeowJson;
import com.github.themeowteam.meowjson.serializer.JsonSerializationException;
import com.github.themeowteam.meowjson.serializer.ObjectSerializer;
import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void testObjectDeserialization() throws JsonSerializationException
    {
        MeowJson instance = new MeowJson();
        ObjectSerializer<TestObject> serializer = (ObjectSerializer<TestObject>) instance.getObjectSerializer(TestObject.class);

        TestObject expectedTestObject = new TestObject();

        JsonObject jsonObject = new JsonObject();
        jsonObject.putInt("testInt", 4);
        jsonObject.putBoolean("testBoolean", false);
        jsonObject.putString("testString", "hello world");

        TestObject deserializedObject = serializer.deserialize(instance, jsonObject);

        Assert.assertEquals(deserializedObject, expectedTestObject);
    }

    public static class TestObject
    {
        public int testInt = 4;
        protected boolean testBoolean = false;
        private String testString = "hello world";

        public TestObject() {}

        @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof TestObject))
                return false;

            TestObject testObj = (TestObject) obj;

            return this.testInt == testObj.testInt && this.testBoolean == testObj.testBoolean && this.testString.equals(testObj.testString);
        }
    }
}
