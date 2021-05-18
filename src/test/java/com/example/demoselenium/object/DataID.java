package com.example.demoselenium.object;

import java.io.Serializable;

public class DataID implements Serializable {
    String id;
    String nameClassBox;
    String dataStatus;
    String workName;


    public DataID() {
    }

    public DataID(String id,String nameClassBox, String dataStatus,String workName) {
        this.id = id;
        this.nameClassBox = nameClassBox;
        this.dataStatus = dataStatus;
        this.workName = workName;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
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
