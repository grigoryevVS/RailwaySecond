package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.User;

import java.util.List;

@Repository
public class PassengerDao extends GenericDao<User, Long>{

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * This method checks passenger registration, did he already
     * registered as a passenger or not yet.
     *
     * @param passenger - target passenger to check.
     * @return - true, if target passenger already exist in the database, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isRegistrationPass(User passenger) {
        String queryString = "from User where firstName='" + passenger.getFirstName() + "'" + " and lastName='" +
                passenger.getLastName() + "'" + " and birthDate='" + passenger.getBirthDate() + "'";
        List<User> passengerList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        return (!passengerList.isEmpty());
    }
}
