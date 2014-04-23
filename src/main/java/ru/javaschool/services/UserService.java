package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.UserDao;
import ru.javaschool.model.entities.User;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * This method implements check, is target user( passed as a parameter )
     * already exist in the database or not.
     * @param user - target user to check.
     * @return - true if we create new one, else return false - such user already exist.
     */
    public boolean isRegistrationSuccess(User user) {
        if(!userDao.isRegistrationPass(user)){
            return false;
        } else {
            userDao.create(user);
            return true;
        }
    }


}
