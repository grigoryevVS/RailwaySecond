package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Train;

import java.util.List;

@Repository
public class TrainDao extends GenericDao<Train, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Find existence of train passsed as parameter in the database
     *
     * @param train - concrete train to check its existence
     * @return - true if it is exist, false another way.
     */
    @SuppressWarnings("unchecked")
    public boolean isTrainExist(Train train) {
        List<Train> trainList = (List<Train>) sessionFactory.getCurrentSession().createQuery("from Train where name='" + train.getName() + "'").list();
        return (!trainList.isEmpty());
    }

    /**
     * To create schedule, we need to find target train of it.
     * @param trainName - name of train
     * @return -  train instance.
     */
    public Train findByName(String trainName) {
        return (Train) sessionFactory.getCurrentSession().createQuery("from Train where name='" + trainName + "'").uniqueResult();
    }
}
