package com.rest.template.model;

public class ExceptionFormat{

    private String message;
    private String path;

    public ExceptionFormat() {
    }

    public ExceptionFormat(String message, String path) {

        this.message = message;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionFormat{" +
                "message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
