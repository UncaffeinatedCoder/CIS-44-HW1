/**
 * Linked list-based implementation of the Queue interface.
 * Uses a singly linked list with head and tail references for O(1) operations.
 * @param <E> The type of elements stored in the queue
 */
public class LinkedQueue<E> implements Queue<E> {
    
    /**
     * Nested Node class for the linked list.
     */
    private static class Node<E> {
        private E element;
        private Node<E> next;
        
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
        
        public E getElement() {
            return element;
        }
        
        public Node<E> getNext() {
            return next;
        }
        
        public void setNext(Node<E> n) {
            next = n;
        }
    }
    
    /** Reference to the head node (front of queue) */
    private Node<E> head = null;
    
    /** Reference to the tail node (rear of queue) */
    private Node<E> tail = null;
    
    /** Number of elements in the queue */
    private int size = 0;
    
    /**
     * Constructs an initially empty queue.
     */
    public LinkedQueue() {
    }
    
    /**
     * Returns the number of elements in the queue.
     * @return number of elements in the queue
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Tests whether the queue is empty.
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Inserts an element at the rear of the queue.
     * @param e the element to be inserted
     */
    @Override
    public void enqueue(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }
    
    /**
     * Returns, but does not remove, the first element of the queue.
     * @return the first element of the queue (or null if empty)
     */
    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }
    
    /**
     * Removes and returns the first element of the queue.
     * @return element removed (or null if empty)
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;  // Queue is now empty
        }
        return answer;
    }
}
