package com.lld.cache.cache;

import com.lld.cache.cache.exceptions.KeyNotFoundException;
import com.lld.cache.cache.policies.EvictionPolicy;
import com.lld.cache.cache.storage.Storage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cache<Key, Value> {

    private final Storage<Key, Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;
    private final Lock lock;

    public Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy){
        this.lock = new ReentrantLock();
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public void put(Key key, Value value){

        try{
            lock.lock();
            Thread.sleep(20);
            this.removeKey(key);

            if(this.storage.isFull()){
                Key keyToRemove =this.evictionPolicy.keyToRemove();
                this.storage.remove(keyToRemove);
            }

            this.storage.put(key, value);
            this.evictionPolicy.accessedKey(key);


        } catch (Exception ex){
            System.out.println("exception occurred");
        } finally {
            lock.unlock();
        }
    }

    public Value get(Key key){

        try{
            lock.lock();
            Value value = this.storage.getValue(key);
            this.evictionPolicy.accessedKey(key);
            return value;

        } catch (KeyNotFoundException ex){
            throw ex;
        } finally {
            lock.unlock();
        }
    }

    public void removeKey(Key key){
        this.storage.remove(key);
    }

    public void remove(){
        Key keyToRemove = this.evictionPolicy.keyToRemove();
        this.storage.remove(keyToRemove);
    }

    public void printCache(){
        this.storage.printContent();
    }
}
