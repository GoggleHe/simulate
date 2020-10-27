package com.example.simulate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 **/
@Controller
@RequestMapping("thread")
public class ThreadController {

    private static final Object lock1 = new Object();

    private static final Object lock2 = new Object();

    private static final Lock lockA = new ReentrantLock();

    private static final Lock lockB = new ReentrantLock();

    @GetMapping("deadLock/reentrantLock")
    @ResponseBody
    public String deadLockReentrantLock() {
        Thread thread1 = new Thread(() -> {
            lockA.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockB.lock();
            System.out.println("exe 1");
            lockB.unlock();
            lockA.unlock();
        });

        Thread thread2 = new Thread(() -> {
            lockB.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockA.lock();
            System.out.println("exe 2");
            lockA.unlock();
            lockB.unlock();
        });

        thread1.start();
        thread2.start();


        return "ok";
    }

    @GetMapping("deadLock/synchronized")
    @ResponseBody
    public String deadLockSynchronized() {

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("hahahahaha1");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("exe 1");
                }

            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("hahahahaha2");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("exe 2");
                }

            }
        });

        thread1.start();
        thread2.start();


        return "ok";
    }

}
