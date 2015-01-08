package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.join.android.app.common.db.tables.Order;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 下午2:30
 */
public class OrderManager extends BaseManager<Order> {

    private static OrderManager orderManager;
    private static RuntimeExceptionDao<Order, Integer> dao;

    private OrderManager() {
        super(dao);
    }

    public static OrderManager getInstance() {
        if (orderManager == null) {
            dao = DBManager.getInstance(null).getHelper().getOrderDao();
            orderManager = new OrderManager();
        }
        return orderManager;
    }
}
