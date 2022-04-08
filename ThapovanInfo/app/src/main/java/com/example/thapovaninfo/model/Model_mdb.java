package com.example.thapovaninfo.model;

public class Model_mdb {

    private String type;
    private String Status;

    public Model_mdb(String type, String Status) {
        this.type = type;
        this.Status = Status;

    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return Status;
    }

}
