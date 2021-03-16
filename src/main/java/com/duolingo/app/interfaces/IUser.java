package com.duolingo.app.interfaces;

import com.duolingo.app.model.User;

import java.util.List;

public interface IUser {

    public User getUserData(String KEYID_USERNAME);

    public User getUserByID(int idUser);

    public boolean loginUser(String userName, String password);

    public boolean registerUser(String userName, String email, String pass, int idOriginLang);

    public boolean changeProfilePic(String KEY_USER, Object image);

    public List<User> getRanking(int idRank);

    public boolean deleteUser(int idUser);

    public boolean buyItem(int idUser, int idItem);

    public User updateUser(User readObject);

    public User parseJSON(String readUTF);
}
