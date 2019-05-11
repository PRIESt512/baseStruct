package ru.base.struct.array;

public class MatrixArray<T> implements IArray<T> {

    private int size;
    private int vector;
    private IArray<IArray<T>> array;

    public MatrixArray(int vector) {
        this.vector = vector;
        array = new SingleArray<>();
        size = 0;
    }

    public MatrixArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == array.size() * vector)
            array.add(new VectorArray<>(vector));
        array.get(size / vector).add(item);
        size++;
    }

    @Override
    public T get(int index) {
        return array.get(index / vector).get(index % vector);
    }

    @Override
    public void set(T item, int index) {
        array.get(index / vector).set(item, index % vector);
    }

    @Override
    public void add(T item, int index) {
        if (index > size()) {
            throw new IllegalArgumentException("Некорректный индекс");
        }
        insert(item, index);
        size++;
    }

    /**
     * Рекурсивный метод вставки.
     * Проверяет допустимые диапазоны для корректного обновления индексов матрицы массивов,
     * чтобы не потерят элементы при поиске по индексу, сдвигая элементы вперед относительно места вставки
     */
    private void insert(T item, int index) {
        int index1 = index / vector;
        int index2 = index % vector;

        if (this.vector - this.array.get(index1).size() == 0) {//сдвигаем элементы вперед относительно места вставки
            T itemOld = array.get(index1).remove(vector - 1); //удаляем последний элемент в строке матрицы, если следующий элемент строки превысит vector
            array.get(index1).add(item, index2);//добавляем новый элемент в нужную нам позицию

            int newIndex1 = index1 + 1;
            int newIndex4itemOld = this.vector * newIndex1;//вычисляем индекс элемента в новой строке

            int rangeMatrix = array.size() - 1;
            if (rangeMatrix != newIndex1) {//если следующей строки не существует
                array.add(new VectorArray<>(vector));//создаем новую строку
                array.get(newIndex1).add(itemOld);
                return;
            }
            insert(itemOld, newIndex4itemOld);//рекурсивно добавляем новый элемент в новую строку
            return;
        }
        array.get(index1).add(item, index2);
    }

    @Override
    public T remove(int index) {
        if (index > size()) {
            throw new IllegalArgumentException("Некорректный индекс");
        }

        T itemOld = array.get(index / vector).get(index % vector);
        delete(index);
        size--;
        return itemOld;
    }

    /**
     * Рекурсивный метод удаления. Логика работы идентична {@link MatrixArray#insert(java.lang.Object, int)}
     */
    private void delete(int index) {
        int index1 = index / vector;
        int index2 = index % vector;

        int copyIndex1 = index1 + 1;
        int rangeMatrix = array.size() - 1;

        array.get(index1).remove(index2);

        if (rangeMatrix >= copyIndex1) {//если следующая строка существует
            T copyItem = array.get(copyIndex1).get(0);
            array.get(index1).add(copyItem, vector - 1);//копируем первый элемент из следующей строки в конец предыдущей строки
            delete(this.vector * copyIndex1);//удаляем элемент из следующей строки
        }
    }
}