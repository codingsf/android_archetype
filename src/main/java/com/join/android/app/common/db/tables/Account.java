package com.join.android.app.common.db.tables;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-11
 * Time: 上午9:11
 */
public class Account {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(index = true)
    private String name;
    @ForeignCollectionField(eager = false)
    ForeignCollection<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Order> getOrders() {
        return orders;
    }

    public void setOrders(ForeignCollection<Order> orders) {
        this.orders = orders;
    }
}
