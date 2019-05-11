package ru.base.struct.priority.queue;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Класс-заявка, содержащий приоритет-значение
 * <p>
 * Этот класс имеет натуральный порядок, не согласованный с <code>equals</code>
 *
 * @param <T>
 */
public class Proposal<T> implements Comparable<Proposal> {

    private final int priority;

    private final List<T> item = new List<>();

    public Proposal(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Proposal o) {
        if (this.priority > o.priority) {
            return 1;
        } else if (this.priority < o.priority) {
            return -1;
        }
        return 0;
    }

    public int getPriority() {
        return priority;
    }

    public List<T> getItems() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposal<?> proposal = (Proposal<?>) o;
        return priority == proposal.priority &&
                Objects.equals(item, proposal.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority, item);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Proposal.class.getSimpleName() + "[", "]")
                .add("priority=" + priority)
                .add("item=" + item)
                .toString();
    }
}
