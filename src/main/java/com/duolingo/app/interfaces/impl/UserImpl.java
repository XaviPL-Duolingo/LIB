package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.IUser;
import com.duolingo.app.model.Language;
import com.duolingo.app.model.User;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserImpl implements IUser{

    @Override
    public User getUserData(int KEYID_USERNAME) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            User u1 = (User) session.get(User.class, KEYID_USERNAME);

            if (u1 != null) {
                return u1;
            }else {
                System.out.println("Error: Ha dado NULL...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean loginUser(String userName, String password) {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();

            Long query = ((Long) session.createQuery("SELECT COUNT(*) FROM User WHERE username = '" + userName + "' AND password = MD5('" + password + "')").uniqueResult());
            int result = query.intValue();
            System.out.println(result);
            if (result == 1){
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

            return false;
    }

    @Override
    public void registerUser(String[] userData) {

        LanguageImpl languageManager = new LanguageImpl();
        RankImpl rankManager = new RankImpl();

        // Datos proporcionados por el usuario

        User newUser = new User();
        newUser.setUsername(userData[0]);
        newUser.setEmail(userData[1]);
        newUser.setPassword(userData[2]);
        newUser.setIdOriginLang(languageManager.getLanguageByID(Integer.parseInt(userData[3])));

        // Datos por defecto al crear usuario

        newUser.setAvatar("https://icon-library.com/images/default-profile-icon/default-profile-icon-24.jpg");
        newUser.setIdRank(rankManager.getRankByID(1));
        newUser.setElo(0);
        newUser.setXp(0);
        newUser.setMoney(0);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(newUser);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
