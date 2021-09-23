package cn.com.demo;

import cn.com.demo.pojo.Person;
import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

/**
 *
 */
public class PriorityBlockingQueueTest {


    @Test
    public void test1() throws Exception {
        PriorityBlockingQueue<Person> pbq = new PriorityBlockingQueue<Person>();
        pbq.add(new Person(4, "A"));
        System.err.println("容器1为：" + pbq);
        pbq.add(new Person(1, "B"));
        System.err.println("容器1为：" + pbq);
        pbq.add(new Person(3, "C"));
        System.err.println("容器1为：" + pbq);
        pbq.add(new Person(2, "D"));
        System.err.println("容器1为：" + pbq);
    }

    @Test
    public void test2() {
        PriorityBlockingQueue<Person> pbq2 = new PriorityBlockingQueue<Person>();
        pbq2.add(new Person(4, "A"));
        System.err.println("容器2为：" + pbq2);
        pbq2.add(new Person(1, "B"));
        System.err.println("容器2为：" + pbq2);
        pbq2.poll();
        pbq2.add(new Person(3, "C"));
        System.err.println("容器2为：" + pbq2);
        pbq2.add(new Person(2, "D"));
        System.err.println("容器2为：" + pbq2);
    }
}
