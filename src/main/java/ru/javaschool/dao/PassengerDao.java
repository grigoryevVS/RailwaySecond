package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Passenger;

import java.util.List;

@Repository
public class PassengerDao extends GenericDao{

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * This method checks passenger registration, did he already
     * registered as a passenger once.
     *
     * @param passenger - target passenger to check.
     * @return - true, if he already exist in the database, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isRegistrationPass(Passenger passenger) {
        String queryString = "from Passenger where firstName='" + passenger.getFirstName() + "'" + " and lastName='" +
                passenger.getLastName() + "'" + " and birthDate='" + passenger.getBirthDate() + "'";
        List<Passenger> passengerList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        return (!passengerList.isEmpty());
    }
}
