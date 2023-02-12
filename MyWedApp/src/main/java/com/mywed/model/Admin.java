package com.mywed.model;


import jakarta.persistence.*;
import org.springframework.lang.Nullable;


@Entity
@Table(name = "adminacc")
public class Admin {
    @Id
    @Column(name = "username")
    @Nullable
    private String userName;
    @Column(name = "password")
    @Nullable
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
