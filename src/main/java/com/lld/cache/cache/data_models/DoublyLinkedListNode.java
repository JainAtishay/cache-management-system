package com.lld.cache.cache.data_models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoublyLinkedListNode<E> {

    private E element;
    private DoublyLinkedListNode<E> previous;
    private DoublyLinkedListNode<E> next;

    public DoublyLinkedListNode(E key) {
        this.element = key;
    }

}
