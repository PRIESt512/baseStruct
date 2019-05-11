package ru.base.struct.priority.queue;

public interface IPriorityQueue<T> {

    void enqueue(int priority, T item);

    T dequeue();
}
