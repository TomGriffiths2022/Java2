package org.example.generics;

// when this class is subclassed a replacement for T must be specified (see MovieDao)
public abstract class Dao<T> {

    public abstract void save(T t);
}
