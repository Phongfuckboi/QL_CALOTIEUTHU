package DTO;

import java.security.PrivateKey;
import java.util.ArrayList;

public class user   {

    private  String id,name,pass;

    public user() {
    }

    public user(String id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "user{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
