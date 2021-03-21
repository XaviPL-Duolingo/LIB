package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.IUser;
import com.duolingo.app.model.Item;
import com.duolingo.app.model.Rank;
import com.duolingo.app.model.User;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import java.util.List;

public class UserImpl implements IUser{

    @Override
    public User getUserData(String KEYID_USERNAME) {

        // getUserData()
        // Obtiene y pasa en forma de objeto todos los datos del usuario que tenga como nombre = "KEYID_USERNAME"

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            List<User> userList = session.createQuery("FROM User WHERE username = '" + KEYID_USERNAME +"'").list();
            return userList.get(0);

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByID(int idUser) {

        // getUserByID()
        // Obtiene el USER con la ID proporcionada en forma de objeto.

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            User u1 = (User) session.get(User.class, idUser);

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

        // loginUser()
        // Consulta a la DB si hay coinciencias con el USERNAME y la PASSWORD proporcionadas, si el resultado es de 1
        // se deuvleve TRUE (el login es correcto), si no hay resultados se devuelve FALSE

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            Long query = ((Long) session.createQuery("SELECT COUNT(*) FROM User WHERE username = '" + userName + "' AND password = '" + password + "'").uniqueResult());
            int result = query.intValue();
            if (result == 1){
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

            return false;
    }

    @Override
    public boolean registerUser(String userName, String email, String pass, int idOriginLang) {

        // registerUser()
        // Crea y añade un nuevo USER a la DB con los datos proporcionados, los datos ya vienen verificados.

        LanguageImpl languageManager = new LanguageImpl();
        RankImpl rankManager = new RankImpl();

        // Datos proporcionados por el usuario

        User newUser = new User();
        newUser.setUsername(userName);
        newUser.setEmail(email);
        newUser.setPassword(pass);

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
            return true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean changeProfilePic(String KEY_USER, Object image) {
        return false;
    }

    @Override
    public List<User> getRanking(int idRank) {

        // getRanking()
        // Obtiene todos los USER que tengan el mismo RANK que el solicitante, se utiliza para el RANKING de la APP.

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            List<User> userList = session.createQuery("FROM User WHERE idRank = " + idRank + " ORDER BY elo DESC ").list();
            return userList;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public boolean deleteUser(int idUser) {

        // deleteUser()
        // Elimina de la DB el USER que tenga la ID proporcionada.

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.delete(getUserByID(idUser));
            t.commit();
            return true;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean buyItem(int idUser, int idItem) {

        // buyItem()
        // Añade a la DB el registro que el USER con la ID proporcionada ha comprado el ITEM con la ID proporcionada
        // y resta de la cuenta del usuario el valor del item comprado. Si ha salido bien devuelve TRUE, si ha fallado
        // algo devuelve FALSE y se aborta.

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();

            UserImpl userManager = new UserImpl();
            User userObj = userManager.getUserByID(idUser);

            ItemImpl itemManager = new ItemImpl();
            Item itemBought = itemManager.getItemByID(idItem);

            userObj.getUserItems().add(itemBought);
            userObj.setMoney(userObj.getMoney()-itemBought.getPriceItem());
            session.merge(userObj);
            t.commit();

            return true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User updateUser(User readObject) {

        // updateUser()
        // Actualiza los datos del USER que se haya pasado como parametro. Ademas se comprueba si puede ascender
        // de rango, se utiliza al finalizar un EXERCIE.

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();

            if (readObject.getIdRank().getIdRank() != 6){
                RankImpl rankManager = new RankImpl();
                Rank nextRank = rankManager.getRankByID(readObject.getIdRank().getIdRank() + 1);
                if (readObject.getElo() > nextRank.getEloRank()){
                    readObject.setIdRank(nextRank);
                }
            }

            session.update(readObject);
            t.commit();
            return readObject;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User parseJSON(String readUTF) {

        // parseJSON()
        // Método auxiliar para parsear los datos del USER en formato JSON

        RankImpl rankManager = new RankImpl();

        User userObj = new User();
        JSONObject jsonObject = new JSONObject(readUTF);
        userObj.setIdUser((int) jsonObject.get("idUser"));
        userObj.setUsername((String) jsonObject.get("username"));
        userObj.setPassword((String)jsonObject.get("password"));
        userObj.setEmail((String)jsonObject.get("email"));
        userObj.setMoney((int)jsonObject.get("money"));
        userObj.setXp( (int)jsonObject.get("xp"));
        userObj.setElo( (int) jsonObject.get("elo"));
        userObj.setAvatar( (String)jsonObject.get("avatar"));
        userObj.setIdRank(rankManager.getRankByID((int) jsonObject.get("idRank")));
        return userObj;
    }


}
