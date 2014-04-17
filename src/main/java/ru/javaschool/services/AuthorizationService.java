package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.AuthorizationDao;

@Service
@Transactional
public class AuthorizationService {

    @Autowired
    private AuthorizationDao authorizationDao;




}
