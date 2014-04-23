package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.UserDao;
import ru.javaschool.model.entities.Ticket;
import ru.javaschool.model.entities.User;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * This method implements check, is target user( passed as a parameter )
     * already exist in the database or not.
     *
     * @param user - target user to check.
     * @return - true if we create new one, else return false - such user already exist.
     */
    public boolean isRegistrationSuccess(User user) {
        if (!userDao.isRegistrationPass(user)) {
            userDao.create(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get target user by identifier, passed as a parameter.
     *
     * @param userId - targets identifier.
     * @return - user instance.
     */
    public User getUserByPk(Long userId) {
        return userDao.findByPK(User.class, userId);
    }


    /**
     * Get list of users tickets.
     *
     * @param userId - target user identifier.
     * @return - list of users tickets.
     */
    public List<Ticket> getUsersTicketList(Long userId) {
        User user = userDao.findByPK(User.class, userId);
        return user.getTicketList();
    }
}
