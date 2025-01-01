package com.lld.cache.cache.data_models;

public class DoublyLinkedList<E> {

    private DoublyLinkedListNode<E> firstNode;
    private DoublyLinkedListNode<E> lastNode;

    public DoublyLinkedList(){
    }

    public void detachNode(DoublyLinkedListNode<E> node) {
        DoublyLinkedListNode<E> previousNode = node.getPrevious();
        DoublyLinkedListNode<E> nextNode = node.getNext();

        if(previousNode != null){
            previousNode.setNext(nextNode);
        }

        if(nextNode != null){
            nextNode.setPrevious(previousNode);
        }

        if(node == lastNode){
            lastNode = node.getPrevious();
        }

        if(node == firstNode){
            firstNode = node.getNext();
        }

    }

    public void addNodeAtLast(DoublyLinkedListNode<E> node) {
        if(lastNode != null){
            this.lastNode.setNext(node);
            node.setPrevious(this.lastNode);
            this.lastNode = node;
        } else{
            this.firstNode = node;
            this.lastNode = node;
        }

    }

    public DoublyLinkedListNode<E> getFirstNode() {
        return firstNode;
    }
}
