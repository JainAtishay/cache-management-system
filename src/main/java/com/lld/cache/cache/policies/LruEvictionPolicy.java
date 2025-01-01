package com.lld.cache.cache.policies;

import com.lld.cache.cache.data_models.DoublyLinkedList;
import com.lld.cache.cache.data_models.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LruEvictionPolicy<Key> implements EvictionPolicy<Key> {
    DoublyLinkedList<Key> dll;
    Map<Key, DoublyLinkedListNode<Key>> map;

    public LruEvictionPolicy(){
        this.dll = new DoublyLinkedList<>();
        this.map = new HashMap<>();
    }

    public void accessedKey(Key key) {
        if(map.containsKey(key)){
            this.dll.detachNode(map.get(key));
            this.dll.addNodeAtLast(map.get(key));
        } else{
            DoublyLinkedListNode<Key> newNode = new DoublyLinkedListNode<Key>(key);
            map.put(key, newNode);
            this.dll.addNodeAtLast(newNode);
        }
    }

    public Key keyToRemove() {
        DoublyLinkedListNode<Key> node = this.dll.getFirstNode();
        map.remove(node.getElement());
        this.dll.detachNode(node);
        return node.getElement();
    }
}
