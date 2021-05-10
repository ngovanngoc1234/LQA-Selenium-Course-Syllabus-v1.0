package com.example.demoselenium.object;

import java.io.Serializable;

public class Content implements Serializable {
    String dataId;
    String projectId;
    String dataStatus;
    String dataStatusLocalName;
    String workerNickname;
    String checkerNickname;
    String workedAt;
    String checkedAt;
    String updatedAt;
    boolean edited;
    boolean editable;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getDataStatusLocalName() {
        return dataStatusLocalName;
    }

    public void setDataStatusLocalName(String dataStatusLocalName) {
        this.dataStatusLocalName = dataStatusLocalName;
    }

    public String getWorkerNickname() {
        return workerNickname;
    }

    public void setWorkerNickname(String workerNickname) {
        this.workerNickname = workerNickname;
    }

    public String getCheckerNickname() {
        return checkerNickname;
    }

    public void setCheckerNickname(String checkerNickname) {
        this.checkerNickname = checkerNickname;
    }

    public String getWorkedAt() {
        return workedAt;
    }

    public void setWorkedAt(String workedAt) {
        this.workedAt = workedAt;
    }

    public String getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(String checkedAt) {
        this.checkedAt = checkedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
