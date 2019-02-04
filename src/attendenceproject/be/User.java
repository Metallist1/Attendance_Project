/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.be;

/**
 *
 * @author nedas
 */
public class User {

    private String name;
    private String url;
    private int ID;
    private int currentClass;
    private int isTeacher;

    public int getID() {
        return ID;
    }

    public User(String name,  int ID, int isTeacher) {
        this.name = name;
        this.ID = ID;
        this.isTeacher = isTeacher;
    }

    public User(String name, String url, int ID, int currentClass, int isTeacher) {
        this.name = name;
        this.url = url;
        this.ID = ID;
        this.currentClass = currentClass;
        this.isTeacher = isTeacher;
    }

    public int isTeacher() {
        return isTeacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(int currentClass) {
        this.currentClass = currentClass;
    }
}
