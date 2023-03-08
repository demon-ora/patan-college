package com.example.testing;

public class Blogdata {
    private String image;
    private String code;
    private  String description;



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blogdata(String image, String code, String description) {
        this.image = image;
        this.code = code;
        this.description = description;
    }

}
