package ru.javaschool.services;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.UserDao;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This service class implements authentication of new users, check roles and so on.
 */
@Service("userDetailsServiceImpl")
@Transactional
public class userDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        ru.javaschool.model.entities.User user = userDao.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        UserDetails authenticatedUser = new User(
                user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
        return authenticatedUser;
    }
}
