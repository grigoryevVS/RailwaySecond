package ru.javaschool.model.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class User implements Serializable{

    private static final long serialVersionUID = 7322705805494879474L;

    @Id
    @GeneratedValue
    private Long userId;
    @NotEmpty
    @Size(min=2, max=30)
    @Column( nullable = false, length = 20)
    private String firstName;
    @NotEmpty
    @Size(min=2, max=30)
    @Column( nullable = false, length = 20)
    private String lastName;
    @NotNull
    @Past
    @Column( nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @NotEmpty
    @Size(min=1, max=30)
    @Column
    private String login;
    @NotEmpty
    @Size(min=3, max=50)
    @Column
    private String password;
    @Column
    private String role;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Ticket> ticketList;

    public User() {
    }

    public User(Long userId, String firstName, String lastName, Date birthDate, String login, String pass, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.login = login;
        this.password = pass;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!birthDate.equals(user.birthDate) || !firstName.equals(user.firstName) || lastName.equals(user.lastName)){
            return false;
        } else{
            return true;
        }
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", login='" + login + '\'' +
                '}';
    }
}
