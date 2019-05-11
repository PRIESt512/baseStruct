package ru.base.struct.priority.queue;

import java.util.StringJoiner;

public class List<T> {

    private Item<T> head;

    private Item<T> tail;

    public List() {
    }

    public void add(T item) {
        if (head == null && tail == null) {
            head = tail = new Item<>(item);
        } else {
            Item<T> newItem = new Item<>(item);
            tail.next = newItem;
            tail = newItem;
        }
    }

    /**
     * Удаляет из списка по одному элементу, начиная с головы
     *
     * @return первый элемент в списке
     */
    public T remove() {
        if (head == null && tail == null) {
            return null;
        }
        if (head == tail) {
            T item = head.item;
            head = tail = null;
            return item;
        }
        Item<T> item = head;
        head = head.next;
        item.next = null;
        return item.item;
    }

    private class Item<K> {

        K item;

        Item<K> next;

        Item(K item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                    .add("item=" + item)
                    .add("next=" + next)
                    .toString();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", List.class.getSimpleName() + "[", "]")
                .add("head=" + head)
                .add("tail=" + tail)
                .toString();
    }
}
