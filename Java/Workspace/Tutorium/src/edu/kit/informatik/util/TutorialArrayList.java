package edu.kit.informatik.util;

public class TutorialArrayList {

    private Object[] array;
    private int index;

    public TutorialArrayList(int size) {
        array = new Object[size];
        index = 0;
    }

    public TutorialArrayList() {
        this(10);
    }

    public void add(Object item) {

        if (index == array.length)
            increase();

        array[index] = item;
        index++;
    }

    private void increase() {
        Object[] newArray = new Object[array.length * 2];
        
        for(int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        
        array = newArray;
    }
    
    public Object getAt(int index) {
        return array[index];
    }
    
    public int getReservedSize() {
        return array.length;
    }
}
