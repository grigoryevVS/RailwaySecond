package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Train;

import java.util.List;

@Repository
public class ScheduleDao extends GenericDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public boolean isTrainInSchedule(Train train) {
        List<Train> trainList = sessionFactory.getCurrentSession().createQuery("from Schedule where train.trainId=" + train.getTrainId()).list();
        return (!trainList.isEmpty());
    }
}
