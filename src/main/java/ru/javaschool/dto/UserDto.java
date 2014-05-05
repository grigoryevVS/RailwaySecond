package ru.javaschool.dto;


import org.hibernate.validator.constraints.NotEmpty;
import ru.javaschool.model.entities.User;

import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class user representation in the view
 * with string parameters and validation on them
 */
public class UserDto {

    private Long id;
    @NotEmpty
    @Size(min = 1, max = 30)
    private String login;
    @NotEmpty
    @Size(min = 3, max = 50)
    private String password;
    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;
    private String role;
    @NotEmpty
    private String birthDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getUserId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.birthDate = sdf.format(user.getBirthDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Method implements getting user instance from
     * userDto object
     *
     * @return - user instance
     */
    public User getUser() {
        User user = new User();
        user.setUserId(this.getId());
        user.setLogin(this.getLogin());
        user.setPassword(this.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setRole(this.getRole());
        try {
            user.setBirthDate(sdf.parse(this.getBirthDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }
}
