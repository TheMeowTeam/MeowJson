package com.github.themeowteam.meowjson;

import java.util.*;

public class JsonArray implements JsonElement, List<JsonElement>
{
    private final List<JsonElement> values;

    public JsonArray()
    {
        this.values = this.createValueList();
    }

    @Override
    public boolean add(JsonElement jsonElement)
    {
        return this.values.add(jsonElement);
    }

    @Override
    public void add(int index, JsonElement element)
    {
        this.values.add(index, element);
    }

    public void addShort(short value)
    {
        this.add(new JsonPrimitive(value));
    }

    public void addInt(int value)
    {
        this.add(new JsonPrimitive(value));
    }

    public void addLong(long value)
    {
        this.add(new JsonPrimitive(value));
    }

    public void addFloat(float value)
    {
        this.add(new JsonPrimitive(value));
    }

    public void addDouble(double value)
    {
        this.add(new JsonPrimitive(value));
    }

    public void addBoolean(boolean value)
    {
        this.add(new JsonPrimitive(value));
    }

    public void addString(String value)
    {
        this.add(new JsonPrimitive(value));
    }

    public void addJsonObject(JsonObject value)
    {
        this.add(value);
    }

    public void addJsonArray(JsonArray value)
    {
        this.add(value);
    }

    public void addNull()
    {
        this.add(JsonNull.INSTANCE);
    }

    public void addShort(int index, short value)
    {
        this.add(index, new JsonPrimitive(value));
    }

    public void addInt(int index, int value)
    {
        this.add(index, new JsonPrimitive(value));
    }

    public void addLong(int index, long value)
    {
        this.add(index, new JsonPrimitive(value));
    }

    public void addFloat(int index, float value)
    {
        this.add(index, new JsonPrimitive(value));
    }

    public void addDouble(int index, double value)
    {
        this.add(index, new JsonPrimitive(value));
    }

    public void addBoolean(int index, boolean value)
    {
        this.add(index, new JsonPrimitive(value));
    }

    public void addString(int index, String value)
    {
        this.add(index, new JsonPrimitive(value));
    }

    public void addJsonObject(int index, JsonObject value)
    {
        this.add(index, value);
    }

    public void addJsonArray(int index, JsonArray value)
    {
        this.add(index, value);
    }

    public void addNull(int index)
    {
        this.values.add(index, JsonNull.INSTANCE);
    }

    @Override
    public boolean addAll(Collection<? extends JsonElement> c)
    {
        return this.values.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends JsonElement> c)
    {
        return this.values.addAll(index, c);
    }

    public boolean addAll(JsonArray jsonArray)
    {
        return this.addAll(jsonArray.values);
    }

    public boolean addAll(int index, JsonArray jsonArray)
    {
        return this.addAll(index, jsonArray.values);
    }

    @Override
    public boolean remove(Object o)
    {
        return this.values.remove(o);
    }

    @Override
    public JsonElement remove(int index)
    {
        return this.values.remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return this.values.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return this.values.containsAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return this.values.retainAll(c);
    }

    @Override
    public JsonElement get(int index)
    {
        return this.values.get(index);
    }

    @Override
    public JsonElement set(int index, JsonElement element)
    {
        return this.values.set(index, element);
    }

    @Override
    public void clear()
    {
        this.values.clear();
    }

    @Override
    public Iterator<JsonElement> iterator()
    {
        return this.values.iterator();
    }

    @Override
    public ListIterator<JsonElement> listIterator()
    {
        return this.values.listIterator();
    }

    @Override
    public ListIterator<JsonElement> listIterator(int index)
    {
        return this.values.listIterator(index);
    }

    @Override
    public List<JsonElement> subList(int fromIndex, int toIndex)
    {
        return this.values.subList(fromIndex, toIndex);
    }

    @Override
    public int size()
    {
        return this.values.size();
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public boolean contains(Object o)
    {
        return this.values.contains(o);
    }

    @Override
    public Object[] toArray()
    {
        return this.values.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return this.values.toArray(a);
    }

    @Override
    public boolean isEmpty()
    {
        return this.values.isEmpty();
    }

    @Override
    public String asString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        Iterator<JsonElement> iterator = this.iterator();

        while (iterator.hasNext())
        {
            stringBuilder.append(iterator.next().asString());

            if (iterator.hasNext())
                stringBuilder.append(",");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    protected List<JsonElement> createValueList()
    {
        return new LinkedList<>();
    }
}
