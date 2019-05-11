package ru.base.struct.priority.queue;

import ru.base.struct.array.IArray;
import ru.base.struct.array.VectorArray;

import java.util.function.BiPredicate;

/**
 * Очередь на основе приоритетов
 *
 * @param <T>
 */
public class PQueue<T> implements IPriorityQueue<T> {

    private final boolean isLazy;

    private IArray<Proposal<T>> proposalIArray;

    private boolean isSorted = false;

    private boolean isLess = false;

    /**
     * @param isLazy если <code>true</code>, то сортировка выполняется на этапе выборки из очереди,
     *               в противном случае на этапе вставки
     */
    public PQueue(boolean isLazy) {
        proposalIArray = new VectorArray<>();
        this.isLazy = isLazy;
    }

    /**
     * @param isLazy если <code>true</code>, то сортировка выполняется на этапе выборки из очереди,
     *               в противном случае на этапе вставки
     * @param isLess если <code>true</code>, тогда при выборке из очереди получаем запись с наименьшим приоритетом
     */
    public PQueue(boolean isLazy, boolean isLess) {
        proposalIArray = new VectorArray<>();
        this.isLazy = isLazy;
        this.isLess = isLess;
    }

    @Override
    public void enqueue(int priority, T item) {
        Proposal<T> proposal = searchPriority(priority);
        if (proposal == null) {
            proposal = new Proposal<>(priority);
            proposalIArray.add(proposal);
        }
        proposal.getItems().add(item);
        isSorted = false;

        if (!isLazy) {
            if (isLess)
                sort(this::less);
            else
                sort(this::more);
            isSorted = true;
        }
    }

    private Proposal<T> searchPriority(int priority) {
        Proposal<T> item = null;
        for (int i = 0; i < proposalIArray.size(); i++) {
            if (proposalIArray.get(i).getPriority() == priority) {
                item = proposalIArray.get(i);
                break;
            }
        }
        return item;
    }

    /**
     * Сортировка вставками
     */
    private void sort(BiPredicate<Proposal<T>, Proposal<T>> predicate) {
        int N = proposalIArray.size();
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && predicate.test(proposalIArray.get(j), proposalIArray.get(j - 1)); j--) {
                exch(j, j - 1);
            }
        }
    }

    private boolean more(Proposal<T> v, Proposal<T> w) {
        return v.compareTo(w) > 0;
    }

    private boolean less(Proposal<T> v, Proposal<T> w) {
        return v.compareTo(w) < 0;
    }

    private void exch(int i, int j) {
        Proposal<T> t = proposalIArray.get(i);
        proposalIArray.set(proposalIArray.get(j), i);
        proposalIArray.set(t, j);
    }

    public T dequeue() {
        if (proposalIArray.size() == 0) {
            return null;
        }
        if (isLazy && !isSorted) {
            if (isLess)
                sort(this::less);
            else
                sort(this::more);
            isSorted = true;
        }
        T item = proposalIArray.get(0).getItems().remove();
        if (item == null) {
            proposalIArray.remove(0);
            return dequeue();
        }
        return item;
    }
}
