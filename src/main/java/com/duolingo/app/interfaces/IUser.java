package com.duolingo.app.interfaces;

import com.duolingo.app.model.User;

public interface IUser {

    public User getUserData();

    public void loginUser();

    public void registerUser(String[] userData);

}
