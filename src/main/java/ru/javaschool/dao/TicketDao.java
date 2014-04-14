package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao<Ticket, Long> extends GenericDao {

    @Autowired
    private SessionFactory sessionFactory;
}
