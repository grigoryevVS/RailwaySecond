package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StationDao<Station, Long> extends GenericDao{

    @Autowired
    private SessionFactory sessionFactory;

}
