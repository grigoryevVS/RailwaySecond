package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.User;

import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class UserDao extends GenericDao<User, Long>{

    @Autowired
    private SessionFactory sessionFactory;


    /**
     * This method checks passenger registration, did he already
     * registered or not yet.
     *
     * @param passenger - target passenger to check.
     * @return - true, if target passenger already exist in the database, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isRegistrationPass(User passenger) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String queryString = "from User where firstName='" + passenger.getFirstName() + "'" + " and lastName='" +
                passenger.getLastName() + "'" + " AND DATE_FORMAT(birthDate,'%Y-%m-%d')='" +
                sdf.format(passenger.getBirthDate())+"'";
        List<User> passengerList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        return (!passengerList.isEmpty());
    }

    /**
     * Get target user by login.
     * @param login - login of target user.
     * @return - user instance.
     */
    @SuppressWarnings("unchecked")
    public User getUserByLogin(String login) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User where login='" + login + "'").uniqueResult();
    }

    public boolean loginExist(String login) {
        User user = (User) sessionFactory.getCurrentSession().createQuery("from User where login='" + login + "'").uniqueResult();
        return user != null;
    }
}
