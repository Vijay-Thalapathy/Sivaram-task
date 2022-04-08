package com.example.thapovaninfo.model;

public class Model_mredis {

    private String type;
    private String Status;

    public Model_mredis(String type, String Status) {
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
