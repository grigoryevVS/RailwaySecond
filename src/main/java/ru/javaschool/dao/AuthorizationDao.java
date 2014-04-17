package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.EmployeeData;

import java.util.List;

@Repository
public class AuthorizationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<EmployeeData> getActualLogins() {
        return sessionFactory.getCurrentSession().createQuery("from EmployeeData").list();
    }

    @SuppressWarnings("unchecked")
    public boolean isExistLoginData(String login, String password) {
        String queryString = "from EmployeeData where login='" + login + "'" + "and password='" + password + "'";
        List<EmployeeData> employeeDataList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        return (!employeeDataList.isEmpty());
    }


}
