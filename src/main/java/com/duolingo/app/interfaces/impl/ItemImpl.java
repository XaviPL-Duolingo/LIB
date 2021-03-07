package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.IItem;
import com.duolingo.app.model.Course;
import com.duolingo.app.model.Item;
import com.duolingo.app.model.Language;
import com.duolingo.app.model.Level;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class ItemImpl implements IItem{

    @Override
    public List<Item> getAllItems() {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            t = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
            criteria.from(Item.class);

            List <Item> listItem = session.createQuery(criteria).getResultList();

            return listItem;

        }
    }

    @Override
    public Item getItemByID(int idItem) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Item i1 = (Item) session.get(Item.class, idItem);

            if (i1 != null) {
                return i1;
            }else {
                System.out.println("Error: Ha dado NULL...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void insertItem(String nameItem, String description, int priceItem) {

        Item itemObj = new Item();
        itemObj.setNameItem(nameItem);
        itemObj.setDescription(description);
        itemObj.setPriceItem(priceItem);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(itemObj);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeItem(int idItem) {

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            Item itemObj = (Item) session.get(Item.class, idItem);
            session.delete(itemObj);
            t.commit();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
