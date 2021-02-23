package com.duolingo.app.interfaces;

import com.duolingo.app.model.Item;

import java.util.List;

public interface IItem {

    public List<Item> getAllItems();

    public void insertItem(String nameItem, String description, int priceItem);

    public void removeItem(int idItem);

}
