package com.example.javaspringrestapi.services;

import com.example.javaspringrestapi.entities.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ObjectProvider<User> customUser;
    private final ObjectProvider<User> fakeUser;

    public UserService(ObjectProvider<User> customUser,
                       @Qualifier("fakeUser") ObjectProvider<User> fakeUser) {
        this.customUser = customUser;
        this.fakeUser = fakeUser;
    }

    public User getCustom(String username, String email, String password){
        User u = customUser.getObject();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        return u;
    }

    public User getFake(){
        return fakeUser.getObject();
    }
}
