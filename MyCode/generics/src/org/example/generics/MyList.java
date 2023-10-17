package org.example.generics;

/*
 * The diamond after the class name indicates a generic class
 * The T is a type parameter; it will be replaced at instantiation time with a real type
 */
public class MyList<T> {
    private Object[] contents = new Object[10];

    private int size = 0;

    public void add(T element){
        if (size < 10) {
            contents[size] = element;
            size += 1;
        } else {
            // ?
        }
    }

    public T get( int index) {
        if (index >= 0 && index < size) {
            return (T) contents[index];
        } else {
            // ?
            return null;
        }
    }
}
