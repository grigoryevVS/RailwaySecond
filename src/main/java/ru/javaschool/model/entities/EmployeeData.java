package ru.javaschool.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class EmployeeData implements Serializable {

    private static final long serialVersionUID = 5820203876295346584L;
    @Id
    @GeneratedValue
    private long adminId;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

    public EmployeeData() {
    }

    public EmployeeData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public EmployeeData(long adminId, String login, String password) {
        this.adminId = adminId;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAdminId() {
        return adminId;
    }
}
