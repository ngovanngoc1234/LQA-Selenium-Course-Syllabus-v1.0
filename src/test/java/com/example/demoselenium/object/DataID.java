package com.example.demoselenium.object;

import java.io.Serializable;

public class DataID implements Serializable {
    String id;
    String nameClassBox;


    public DataID() {
    }

    public DataID(String id,String nameClassBox) {
        this.id = id;
        this.nameClassBox = nameClassBox;
    }

    public String getNameClassBox() {
        return nameClassBox;
    }

    public void setNameClassBox(String nameClassBox) {
        this.nameClassBox = nameClassBox;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
