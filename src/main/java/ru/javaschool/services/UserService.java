package ru.javaschool.services;


import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.TicketDao;
import ru.javaschool.dao.UserDao;
import ru.javaschool.dto.TicketDto;
import ru.javaschool.model.entities.Ticket;
import ru.javaschool.model.entities.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TicketDao ticketDao;

    /**
     * This method implements check, is target user( passed as a parameter )
     * already exist in the database or not.
     *
     * @param user - target user to check.
     * @return - true if we create new one, else return false - such user already exist.
     */
    public String isRegistrationSuccess(User user) {
        if (!userDao.loginExist(user.getLogin())) {
            if (!userDao.isRegistrationPass(user)) {
                userDao.create(user);
                return "Success!";
            }
            return "Client " + user.getFirstName() + user.getLastName() + " born in " + user.getBirthDate() + " already registered!";
        }
        return "Login " + user.getLogin() + " is already busy!";
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
     * @return - list of users tickets wrapped by ticketDto to show info on the view.
     */
    public List<TicketDto> getUsersTicketList(Long userId) {
        User user = userDao.findByPK(User.class, userId);
        List<Ticket> tickets = user.getTicketList();
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket t : tickets) {
            ticketDtos.add(new TicketDto(t.getUser(), t.getSchedule(), t.getStationFrom(), t.getStationTo()));
        }
        return ticketDtos;
    }

    /**
     * Update target user
     *
     * @param user - target
     */
    public String updateUser(User user) {
        if (!userDao.loginCheckForUpdate(user)) {
            if (userDao.isUpdateAccess(user)) {
                userDao.update(user);
                return "Success!";
            }
            return "Client " + user.getFirstName() + user.getLastName() + " born in " + user.getBirthDate() + " already registered!";
        }
        return "Login " + user.getLogin() + " is already busy!";

    }

    /**
     * Getting user by his login
     *
     * @param name - users login
     * @return - user instance
     */
    public User getUserByLogin(String name) {
        return userDao.getUserByLogin(name);
    }

    /**
     * Check validity of birth date
     *
     * @param user - target user
     * @return - true if his birth date correct, else return false
     */
    public boolean isCorrectAge(User user) {
        Duration interval = new Duration(3468960000000L);
        DateTime birthDate = new DateTime(user.getBirthDate());
        DateTime currentTime = new DateTime();
        Duration duration = new Duration(birthDate, currentTime);
        LocalDate today = new LocalDate();
        return today.toDate().after(user.getBirthDate()) && duration.isShorterThan(interval);
    }
}
