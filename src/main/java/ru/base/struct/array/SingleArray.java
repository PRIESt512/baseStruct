package ru.base.struct.array;

public class SingleArray<T> implements IArray<T> {

    private Object[] array;

    public SingleArray() {
        array = new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        resize();
        array[size() - 1] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void set(T item, int index) {
        array[index] = item;
    }

    @Override
    public void add(T item, int index) {
        if (index > size()) {
            throw new IllegalArgumentException("Некорректный индекс");
        }
        resize();
        System.arraycopy(array, index, array, index + 1, size() - index - 1);
        array[index] = item;
    }

    @Override
    public T remove(int index) {
        if (index > size()) {
            throw new IllegalArgumentException("Некорректный индекс");
        }
        T removeItem = get(index);
        System.arraycopy(array, index + 1, array, index, size() - index - 1);
        array[size() - 1] = null;
        return removeItem;
    }

    private void resize() {
        Object[] newArray = new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }
}
