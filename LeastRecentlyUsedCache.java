/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.System;
import java.util.*;
/**
 *
 * @author mlewis
 */

//Double Linked List Data Structure (Queue)
class Node {
    Object key;
    Object value;
    Node prev;
    Node post;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}

public class LeastRecentlyUsedCache
        implements LruCache{
    int capacity;
    Node head = null;
    Node tail = null;
    HashMap<Integer, Node> cache = new HashMap<Integer, Node>();

    public LeastRecentlyUsedCache(int capacity){
        this.capacity = capacity;
    }

    public Object get(Object key) {
        if(cache.containsKey(key)){
            //Node exists so grab node, delete from queue, move to head
            Node n = cache.get(key);
            delete(n);
            makeHead(n);
            return n.value;
        }
        //No node found with that key, return null as requirements specified
        return null;
    }

    public void put(Object key, Object value) {
        if(cache.containsKey(key)) {
            //Node exists so set new value
            Node exists = cache.get(key);
            exists.value = value;
            //Move node from current position in queue to head of queue
            delete(exists);
            makeHead(exists);
        }
        else {
            //Node does not exist in cache
            Node newEntry = new Node(key, value);
            if(cache.size() >= capacity) {
                //Cache is full, must remove LRU node
                cache.remove(tail.key);
                delete(tail);
                makeHead(newEntry);
            }
            else {
                //Cache is not full, add new node to beginning of queue
                makeHead(newEntry);
            }
            //Add node to Hash Map for easy retrieval/deletion
            cache.put((int) key, newEntry);
        }
    }

    public int getMaxSize(){
        int space = capacity - cache.size();
        return space;
    }

    public String toString() {
        String cacheList = "CACHE CURRENTLY CONTAINS:\n";
        if(head == null) return "Cache is EMPTY!";
        else {
            Node temp = head;
            while (temp != null) {
                 cacheList += "Key: " + temp.key + " Value: " + temp.value + "\n";
                temp = temp.post;
            }
            return cacheList;
        }
    }

    public void delete(Node n){
        if(n.prev != null) {
            n.prev.post = n.post;
        }
        else{
            head = n.post;
        }

        if(n.post != null) {
            n.post.prev = n.prev;
        }
        else {
            tail = n.prev;
        }
    }

    public void makeHead (Node n) {
        //Set next node for n equal to head as it will now become the head
        n.post = head;
        //Set previous node for n to null as it will now become the new head
        n.prev = null;

        //If head is not null, set previous node to new node n otherwise linked list must be empty
        if(head != null)
            head.prev = n;
        head = n;

        //Check for only node
        if (tail == null)
            tail = head;

    }

    public static void main(String[] args) {

        LeastRecentlyUsedCache lr = new LeastRecentlyUsedCache(5);
        System.out.println(lr.toString());
        lr.put(1, 1);
        lr.put(2, 2);
        System.out.println("Cache currently has room for " + lr.getMaxSize() + " more item(s)");
        System.out.println(lr.toString());
        lr.put(3, 3);
        lr.put(4, 4);
        lr.put(5, 5);
        System.out.println("Cache currently has room for " + lr.getMaxSize() + " more item(s)");
        System.out.println(lr.toString());
        Object val = lr.get(1);
        System.out.println("" + val);
        lr.put(6, 6);
        System.out.println("Cache currently has room for " + lr.getMaxSize() + " more item(s)");
        System.out.println(lr.toString());
        Object val2 = lr.get(2);

        if(val2 == null) System.out.println("Value Not Found");
        else System.out.println("Something went wrong");

    }

}


