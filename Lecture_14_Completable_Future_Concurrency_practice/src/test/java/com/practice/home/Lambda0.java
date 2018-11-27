package com.practice.home;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Lambda0 {


    @Test
    public void testTHreadPoolSize() throws InterruptedException {
        new Thread(() -> {
            foo();
        }).start();
        new Thread(() -> {
            foo();
        }).start();
        new Thread(() -> {
            foo();
        }).start();
    }

    private ReentrantLock lock = new ReentrantLock();
    private static AtomicInteger counter = new AtomicInteger(0);

    public void foo() {
        lock.lock();
        System.out.println("Print " + counter.incrementAndGet());
    }


    /**
     * Complete test and  console output like this:
     * -> Run Before
     * -> Run After
     */
    @Test
    public void testRun() {
        runLater(() -> {
            System.out.println("Before");
            System.out.println("After");
        }).run();
    }


    public Runnable runLater(Runnable runnable) {
        return () -> {
            // : add output to console
            System.out.println("Before");
            runnable.run();
            System.out.println("After");
        };
    }

}