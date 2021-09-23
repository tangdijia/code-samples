package cn.com.demo;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 */
public class ArrayBlockingQueueTest {

    @Test
    public void addTest() {
        System.out.println("add(): 增加一个元索，如果队列已满，则抛出一个IIIegaISlabEepeplian异常");
        ArrayBlockingQueue queue1 = new ArrayBlockingQueue(2);
        for (int i = 0; i < 3; i++) {
            try {
                boolean add = queue1.add("" + i);
                System.out.println("queue add -> " + add);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void offerTest() {
        System.out.println("offer(): 添加一个元素并返回true，如果队列已满，则返回false");
        ArrayBlockingQueue queue2 = new ArrayBlockingQueue(2);
        for (int i = 0; i < 3; i++) {
            try {
                boolean offer = queue2.offer("" + i);
                System.out.println("queue offer -> " + offer);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void putTest() {
        System.out.println("put(): 添加一个元素，如果队列满，则阻塞，等待");
        ArrayBlockingQueue<String> queue3 = new ArrayBlockingQueue<String>(2);
        for (int i = 0; i < 2; i++) {
            try {
                queue3.put("" + i);
                System.out.println(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ArrayBlockingQueue<String> queue1 = new ArrayBlockingQueue<String>(3);
    ArrayBlockingQueue<String> queue2 = new ArrayBlockingQueue<String>(3);
    ArrayBlockingQueue<String> queue3 = new ArrayBlockingQueue<String>(3);

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 3; i++) {
            queue1.add("test" + i);
            queue2.add("test" + i);
        }
    }

    @Test
    public void removeTest() {
        System.out.println("remove，移除并返回队列头部的元素。如果队列为空，则抛出一个NoSuchElementException异常");
        while (true) {
            String v = queue1.remove();
            System.out.println("value: " + v);
        }
    }

    @Test
    public void elementTest() {
        System.out.println("element，返回队列头部的元素，不会出队列。如果队列为空，则抛出一个NoSuchElementException异常");
        for (int i = 0; i < 5; i++) {
            String v = queue1.element();
            System.out.println("value: " + v);
        }
    }

    @Test
    public void pollTest() {
        System.out.println("poll         移除并返问队列头部的元素    如果队列为空，则返回null");
        for (int i = 0; i < 5; i++) {
            String v = queue1.poll();
            System.out.println("value: " + v);
        }
    }

    @Test
    public void peekTest() {
        System.out.println("peek       返回队列头部的元素，不移除             如果队列为空，则返回null");
        for (int i = 0; i < 5; i++) {
            String v = queue1.peek();
            System.out.println("value: " + v);
        }
    }

    @Test
    public void takeTest() {
        System.out.println("take        移除并返回队列头部的元素     如果队列为空，则阻塞");
        for (int i = 0; i < 3; i++) {
            String v = null;
            try {
                v = queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("value: " + v);
        }
    }
}
