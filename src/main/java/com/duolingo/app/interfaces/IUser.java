package com.duolingo.app.interfaces;

import com.duolingo.app.model.User;

public interface IUser {

    public User getUserData(int KEYID_USERNAME);

    public boolean loginUser(String userName, String password);

    public void registerUser(String[] userData);

}
