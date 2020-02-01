package com.ajla.shop.service;

import com.ajla.shop.model.User;

public interface IUserService {
    User findByEmail(final String email);
    void saveDataFromUser(final User user) throws Throwable;
}
