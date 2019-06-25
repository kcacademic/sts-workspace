package com.learning.generics;

interface Sink<T> {
    public void flush(T t);
}