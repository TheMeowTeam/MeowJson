package com.github.themeowteam.meowjson.tests;

import com.github.themeowteam.meowjson.JsonArray;
import com.github.themeowteam.meowjson.JsonNull;
import com.github.themeowteam.meowjson.JsonObject;
import com.github.themeowteam.meowjson.JsonPrimitive;
import com.github.themeowteam.meowjson.concurrent.ConcurrentJsonArray;
import com.github.themeowteam.meowjson.concurrent.ConcurrentJsonObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class JsonTest
{
    @Test
    public void testObjects()
    {
        JsonObject jsonObject = new JsonObject();

        jsonObject.putShort("testShort", (short) 42);
        jsonObject.putInt("testInt", 42);
        jsonObject.putLong("testLong", 42L);
        jsonObject.putFloat("testFloat", 42.42F);
        jsonObject.putDouble("testDouble", 42.42D);
        jsonObject.putBoolean("testBoolean", true);
        jsonObject.putString("testString", "42");
        jsonObject.putNull("testNull");

        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.putInt("testInt2", 42);

        jsonObject.putJsonObject("testObject", jsonObject2);

        jsonObject.putString("testRemove", "42");
        Assert.assertNotNull(jsonObject.remove("testRemove"));

        JsonArray array = new JsonArray();
        array.addShort((short) 42);
        array.addShort(0, (short) 42);
        array.addInt(42);
        array.addInt(0, 42);
        array.addLong(42);
        array.addLong(0, 42);
        array.addFloat(42.42F);
        array.addFloat(0, 42.42F);
        array.addDouble(42.42D);
        array.addDouble(0, 42.42D);
        array.addBoolean(true);
        array.addBoolean(0, true);
        array.addString("42");
        array.addString(0, "42");
        array.addNull();
        array.addNull(0);

        JsonArray array2 = new JsonArray();
        array2.addString("42.1");
        array2.addString("42.2");
        array2.addString("42.3");

        array.addAll(array2);
        array.addAll(0, array2);

        array.addString(0, "testRemove");
        Assert.assertNotNull(array.remove(0));

        jsonObject.putJsonArray("testArray", array);

        Assert.assertEquals(new JsonPrimitive((short) 42), new JsonPrimitive((short) 42));
        Assert.assertEquals(new JsonPrimitive(42), new JsonPrimitive(42));
        Assert.assertEquals(new JsonPrimitive(42L), new JsonPrimitive(42L));
        Assert.assertEquals(new JsonPrimitive(42.42F), new JsonPrimitive(42.42F));
        Assert.assertEquals(new JsonPrimitive(42.42D), new JsonPrimitive(42.42D));
        Assert.assertEquals(new JsonPrimitive(true), new JsonPrimitive(true));
        Assert.assertEquals(new JsonPrimitive("42"), new JsonPrimitive("42"));

        Assert.assertEquals(JsonNull.INSTANCE, new JsonNull());
    }

    /**@Test
    public void testConcurrentJsonObject()
    {
        int threadCount = 100;

        JsonObject jsonObject = new ConcurrentJsonObject();
        AtomicReference<Throwable> failedOnce = new AtomicReference<>(null);

        List<Thread> threads = new ArrayList<>(threadCount);

        for (int i = 0; i < threadCount; ++i)
        {
            int finalI = i;
            threads.add(new Thread("TEST-OBJECT-" + finalI)
            {
                @Override
                public void run()
                {
                    try
                    {
                        for (int j = 0; j < 1000; ++j)
                        {
                            jsonObject.putInt(finalI + "_" + j, 42);
                        }

                        for (int j = 0; j < 1000; ++j)
                        {
                            if (jsonObject.remove(finalI + "_" + j) == null)
                            {
                                failedOnce.set(new Exception("JsonObject#remove returned null"));
                            }
                        }
                    }
                    catch (Throwable throwable)
                    {
                        failedOnce.set(throwable);
                    }
                }
            });
        }

        threads.forEach(Thread::start);

        for (Thread thread : threads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException ex)
            {
                throw new AssertionError(ex);
            }
        }

        Throwable throwable = failedOnce.get();

        if (throwable != null)
        {
            throw new AssertionError(throwable);
        }
    }

    @Test
    public void testConcurrentJsonArray()
    {
        int threadCount = 100;

        JsonArray jsonArray = new ConcurrentJsonArray();
        AtomicReference<Throwable> failedOnce = new AtomicReference<>(null);

        List<Thread> threads = new ArrayList<>(threadCount);

        for (int i = 0; i < threadCount; ++i)
        {
            int finalI = i;
            threads.add(new Thread("TEST-ARRAY-" + finalI)
            {
                @Override
                public void run()
                {
                    try
                    {
                        for (int j = 0; j < 1000; ++j)
                        {
                            jsonArray.addString(finalI + "_" + j);
                        }

                        for (int j = 0; j < 1000; ++j)
                        {
                            if (jsonArray.remove(0) == null)
                            {
                                failedOnce.set(new Exception("JsonArray#remove returned null"));
                            }
                        }
                    }
                    catch (Throwable throwable)
                    {
                        failedOnce.set(throwable);
                    }
                }
            });
        }

        threads.forEach(Thread::start);

        for (Thread thread : threads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException ex)
            {
                throw new AssertionError(ex);
            }
        }

        Throwable throwable = failedOnce.get();

        if (throwable != null)
        {
            throw new AssertionError(throwable);
        }
    }**/
}
