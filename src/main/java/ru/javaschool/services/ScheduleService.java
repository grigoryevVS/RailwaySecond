package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.ScheduleDao;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

//    public List<Schedule> getRevisedScheduleList(ScheduleConstraints constraints) {
//       // TODO impl
//    }


}
