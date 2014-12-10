package com.isistan.util;

import java.lang.reflect.Array;
import java.util.*;

public class Permutations<E> implements  Iterator<E[]>{

    private E[] arr;
    private byte[] ind;
    private boolean has_next;

    public E[] output;//next() returns this array, make it public

    @SuppressWarnings("unchecked")
	public Permutations(E[] arr){
        this.arr = arr.clone();
        ind = new byte[arr.length];
        //convert an array of any elements into array of integers - first occurrence is used to enumerate
        Map<E, Byte> hm = new HashMap<E, Byte>();
        for(byte i = 0; i < arr.length; i++){
            Byte n = hm.get(arr[i]);
            if (n == null){
                hm.put(arr[i], i);
                n = i;
            }
            ind[i] = n.byteValue();
        }
        Arrays.sort(ind);//start with ascending sequence of integers
        //output = new E[arr.length]; <-- cannot do in Java with generics, so use reflection
        output = (E[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        has_next = true;
    }

    public boolean hasNext() {
        return has_next;
    }

    /**
     * Computes next permutations. Same array instance is returned every time!
     * @return
     */
    public E[] next() {
        if (!has_next)
            throw new NoSuchElementException();

        
        for(int i = 0; i < ind.length; i++){
            output[i] = arr[ind[i]];
        }
        
        //get next permutation
        has_next = false;
        for(int tail = ind.length - 1;tail > 0;tail--){
            if (ind[tail - 1] < ind[tail]){//still increasing

                //find last element which does not exceed ind[tail-1]
                int s = ind.length - 1;
                while(ind[tail-1] >= ind[s])
                    s--;

                swap(ind, tail-1, s);

                //reverse order of elements in the tail
                for(int i = tail, j = ind.length - 1; i < j; i++, j--){
                    swap(ind, i, j);
                }
                has_next = true;
                break;
            }
        }
        return output;
    }

    private void swap(byte[] arr, int i, int j){
        byte t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public void remove() {

    }
}