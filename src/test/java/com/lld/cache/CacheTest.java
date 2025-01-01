package com.lld.cache;

import com.lld.cache.cache.Cache;
import com.lld.cache.cache.factories.CacheFactory;
import com.lld.cache.cache.policies.EvictionPolicy;
import com.lld.cache.cache.policies.LruEvictionPolicy;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class CacheTest {

    public static Cache<Integer, Integer> cache = new CacheFactory<Integer, Integer>().defaultCacheFactory(3);

    public static void main(String[] args) throws InterruptedException {
        //testCache();
        //testCacheWithMultiThreadingForBothPut();
        testCacheWithMultiThreadingForPutAndGet();
        //testLruEviction();
    }

    private static void testCache() {
        Cache<Integer, Integer> cache = new CacheFactory<Integer, Integer>().defaultCacheFactory(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);

        Integer value = cache.get(1);
        System.out.println(value);

        cache.put(4,4);

        System.out.println(cache.get(3));
    }


    private static void testCacheWithMultiThreadingForBothPut() throws InterruptedException {

        cache.put(1, 1);
        cache.put(2, 2);

        Thread thread1 = new Thread(() -> {
            cache.put(3, 3);
            cache.put(4, 4);
        });

        Thread thread2 = new Thread(() -> {
            cache.put(5, 5);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        cache.printCache();
    }

    private static void testCacheWithMultiThreadingForPutAndGet() throws InterruptedException {

        cache.put(1, 1);
        cache.put(2, 2);

        Thread thread1 = new Thread(() -> {
            //System.out.println(currentThread().getName() + " updating value : "+20);
            cache.put(2, 20);
        });

        Thread thread2 = new Thread(() -> {
            int value = cache.get(2);
            System.out.println(currentThread().getName() + " read value : "+value);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        cache.printCache();
    }

    public static void computeOperation(int key, int delta){
        int value = cache.get(key);
        System.out.println(Thread.currentThread().getName() + " read value:  "+value);
        cache.put(1, value+delta);
        System.out.println(Thread.currentThread().getName() + " updating value to "+cache.get(key));
    }

    private static void testLruEviction() {
        EvictionPolicy<Integer> evictionPolicy = new LruEvictionPolicy<>();
        evictionPolicy.accessedKey(1);
        evictionPolicy.accessedKey(2);
        evictionPolicy.accessedKey(3);
        evictionPolicy.accessedKey(4);

        System.out.println(evictionPolicy.keyToRemove());

        evictionPolicy.accessedKey(2);

        System.out.println(evictionPolicy.keyToRemove());
        System.out.println(evictionPolicy.keyToRemove());
        System.out.println(evictionPolicy.keyToRemove());
    }
}
