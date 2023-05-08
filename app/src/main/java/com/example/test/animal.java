package com.example.test;

import android.widget.EditText;

public class animal {
    String Name ,age, image   ;

    public animal(EditText name, EditText age, EditText image) {
    }


    public String getName() {
        return Name;
    }

    public String getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        Name = name;
    }
}
