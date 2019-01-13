package com.futsalmanagement.futsalapp.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_group")
public class UserGroup {

    @Id
    private int user_group_id;
    private String user_group_name;
    private String user_group_desc;


    public UserGroup(int user_group_id, String user_group_name, String user_group_desc) {
        this.user_group_id = user_group_id;
        this.user_group_name = user_group_name;
        this.user_group_desc = user_group_desc;
    }
    public UserGroup(String user_group_name){
        this.user_group_name = user_group_name;
    }

    public UserGroup changeFormat(){
        return new UserGroup(this.user_group_name);
    }

    public UserGroup() {
    }

    public int getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(int user_group_id) {
        this.user_group_id = user_group_id;
    }

    public String getUser_group_name() {
        return user_group_name;
    }

    public void setUser_group_name(String user_group_name) {
        this.user_group_name = user_group_name;
    }

    public String getUser_group_desc() {
        return user_group_desc;
    }

    public void setUser_group_desc(String user_group_desc) {
        this.user_group_desc = user_group_desc;
    }
}
