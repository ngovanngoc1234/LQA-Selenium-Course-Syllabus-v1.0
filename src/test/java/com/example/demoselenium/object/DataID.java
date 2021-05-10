package com.example.demoselenium.object;

import java.io.Serializable;

public class DataID implements Serializable {
    String id;


    public DataID() {
    }

    public DataID(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
