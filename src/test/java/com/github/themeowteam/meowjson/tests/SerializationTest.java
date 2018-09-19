package com.github.themeowteam.meowjson.tests;

import com.github.themeowteam.meowjson.JsonElement;
import com.github.themeowteam.meowjson.JsonObject;
import com.github.themeowteam.meowjson.MeowJson;
import com.github.themeowteam.meowjson.serializer.INamedJsonField;
import com.github.themeowteam.meowjson.serializer.JsonSerializationException;
import com.github.themeowteam.meowjson.serializer.ObjectSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
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
        expectedSerializedObject.putLong("myCustomNameTestLong", -1);

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
        jsonObject.putLong("myCustomNameTestLong", -1);

        TestObject deserializedObject = serializer.deserialize(instance, jsonObject);

        Assert.assertEquals(deserializedObject, expectedTestObject);
    }

    @Test
    public void testRecursiveObjectSerialization() throws JsonSerializationException
    {
        MeowJson instance = new MeowJson();
        ObjectSerializer<TestRecursiveObject> serializer = (ObjectSerializer<TestRecursiveObject>)
                instance.getObjectSerializer(TestRecursiveObject.class);

        TestRecursiveObject testRecursiveObject = new TestRecursiveObject();
        JsonElement serializedObject = serializer.serialize(instance, testRecursiveObject);

        JsonObject expectedSerializedObject = new JsonObject();
        JsonObject expectedSerializedObjectRec = new JsonObject();
        expectedSerializedObjectRec.putInt("testInt", 4);
        expectedSerializedObjectRec.putBoolean("testBoolean", false);
        expectedSerializedObjectRec.putString("testString", "hello world");
        expectedSerializedObjectRec.putLong("myCustomNameTestLong", -1);
        expectedSerializedObject.put("testObject", expectedSerializedObjectRec);

        Assert.assertEquals(serializedObject, expectedSerializedObject);
    }

    @Test
    public void testRecursiveObjectDeserialization() throws JsonSerializationException
    {
        MeowJson instance = new MeowJson();
        ObjectSerializer<TestRecursiveObject> serializer = (ObjectSerializer<TestRecursiveObject>)
                instance.getObjectSerializer(TestRecursiveObject.class);

        TestRecursiveObject expectedTestRecursiveObject = new TestRecursiveObject();

        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObjectRec = new JsonObject();
        jsonObjectRec.putInt("testInt", 4);
        jsonObjectRec.putBoolean("testBoolean", false);
        jsonObjectRec.putString("testString", "hello world");
        jsonObjectRec.putLong("myCustomNameTestLong", -1);
        jsonObject.put("testObject", jsonObjectRec);

        TestRecursiveObject deserializedObject = serializer.deserialize(instance, jsonObject);

        Assert.assertEquals(deserializedObject, expectedTestRecursiveObject);
    }

    public static class TestObject
    {
        public int testInt = 4;
        protected boolean testBoolean = false;
        private String testString = "hello world";

        @INamedJsonField(name = "myCustomNameTestLong")
        private long testLong = -1;

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

    public static class TestRecursiveObject
    {
        public TestObject testObject = new TestObject();

        public TestRecursiveObject() {}

        @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof TestRecursiveObject))
                return false;

            return Objects.equals(this.testObject, ((TestRecursiveObject) obj).testObject);
        }
    }
}
