package com.duolingo.app.interfaces;

import com.duolingo.app.model.User;

public interface IUser {

    public User getUserData(String KEYID_USERNAME);

    public boolean loginUser(String userName, String password);

    public boolean registerUser(String userName, String email, String pass, int idOriginLang);

    public boolean changeProfilePic(String KEY_USER, Object image);

    public boolean deleteUser(int KEYID_USERNAME);

}
