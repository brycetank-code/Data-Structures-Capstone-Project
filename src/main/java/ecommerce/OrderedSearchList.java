package ecommerce;

import java.math.BigDecimal;

public class OrderedSearchList {
    
    private static class Node {
        BigDecimal key;
        String value;
        Node prev;
        Node next;
        
        Node(BigDecimal key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final Node head;
    private final Node tail;
    private int size;
    
    public OrderedSearchList() {
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        head.prev = head;
        size = 0;
    }
    
    public String get(BigDecimal price){
        Node node = findNode(price);
        return (node != null) ? node.value : null;
    }
    
    private Node findNode(BigDecimal price) {
        if (price == null) return null;
        
        Node current = head.next;
        while (current != tail) {
            if (current.key.compareTo(price) == 0) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}
