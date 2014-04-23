package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.User;

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
        String queryString = "from User where firstName='" + passenger.getFirstName() + "'" + " and lastName='" +
                passenger.getLastName() + "'" + " and birthDate='" + passenger.getBirthDate() + "'";
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
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User where login='" + login + "'").list();
        return users.get(0);
    }


//    /**
//     * Help method, to get all actual login and passwords
//     *
//     * @return - list of employeeData
//     */
//    @SuppressWarnings("unchecked")
//    public List<EmployeeData> getActualLogins() {
//        return sessionFactory.getCurrentSession().createQuery("from EmployeeData").list();
//    }
//
//    /**
//     * Method checks correctness of input login and password.
//     *
//     * @param login    - target login
//     * @param password - target password
//     * @return - true, if correct, false if incorrect.
//     */
//    @SuppressWarnings("unchecked")
//    public boolean isExistLoginData(String login, String password) {
//        String queryString = "from EmployeeData where login='" + login + "'" + "and password='" + password + "'";
//        List<EmployeeData> employeeDataList = sessionFactory.getCurrentSession().createQuery(queryString).list();
//        return (!employeeDataList.isEmpty());
//    }

}
