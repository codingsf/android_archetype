package com.join.android.app.common.db.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-10
 * Time: 下午4:52
 */
@DatabaseTable(tableName = "t_order")
public class Order {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    public Date date;

    @DatabaseField(columnName = "account_id",foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
