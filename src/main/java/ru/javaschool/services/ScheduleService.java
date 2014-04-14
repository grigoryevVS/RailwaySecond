package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.ScheduleDao;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;
}
