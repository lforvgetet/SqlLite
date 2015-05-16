package com.fis.www.sqllite;

import java.io.Serializable;

/**
 * Created by f103082 on 2015/5/12.
 */
public class Author implements Serializable {
    private long id;
    private String name;
    public Author(){
        id=0;
        name="";

    }
    public Author(String name){this.name=name;}
    public long getId(){return id;}
    public void setId(long id ){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}

}
