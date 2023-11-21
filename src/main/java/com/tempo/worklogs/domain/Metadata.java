package com.tempo.worklogs.domain;

public class Metadata{
    public int count;
    public int offset;
    public int limit;
    public String next;
    @Override
    public String toString() {
        return "Metadata [count=" + count + ", offset=" + offset + ", limit=" + limit + ", next=" + next + "]";
    }

    
}
