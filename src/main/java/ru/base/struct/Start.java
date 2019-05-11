package ru.base.struct;

import ru.base.struct.array.FactorArray;
import ru.base.struct.array.IArray;
import ru.base.struct.array.MatrixArray;
import ru.base.struct.array.SingleArray;
import ru.base.struct.array.VectorArray;
import ru.base.struct.priority.queue.PQueue;

public class Start {

    public static void main(String[] args) {
       /* IArray<Integer> test = new FactorArray<>();
        test.add(1);//0
        test.add(2);//1
        test.add(3);//2
        test.add(4);//3
        test.add(5);//4
        test.add(6);//5

        test.add(5, 0);
        System.out.println(test.get(0));
        System.out.println(test.remove(0));*/

        PQueue<Integer> pQueue = new PQueue<>(true, true);

        pQueue.enqueue(50, 12);
        pQueue.enqueue(65, 21);
        pQueue.enqueue(50, 13);
        pQueue.enqueue(48, 10);

        System.out.println(pQueue.dequeue());
        System.out.println(pQueue.dequeue());
        System.out.println(pQueue.dequeue());
        System.out.println(pQueue.dequeue());
    }
}
