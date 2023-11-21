package com.tempo.worklogs.domain;

import java.util.ArrayList;

public class WorklogResponse{
    public String self;
    public Metadata metadata;
    public ArrayList<Result> results;
    @Override
    public String toString() {
        return "WorklogResponse [self=" + self + ", metadata=" + metadata + ", results=" + results + "]";
    }

    
}








