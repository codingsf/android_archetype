package com.join.android.app.common.activity;

import android.util.Log;
import android.widget.TextView;
import com.BaseActivity;
import com.j256.ormlite.dao.CloseableIterable;
import com.join.android.app.mgps.R;
import com.join.android.app.common.db.manager.AccountManager;
import com.join.android.app.common.db.manager.OrderManager;
import com.join.android.app.common.db.tables.Account;
import com.join.android.app.common.db.tables.Order;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@EActivity(R.layout.main)
public class DBActivity extends BaseActivity{
    private final String LOG_TAG = getClass().getSimpleName();
	@StringRes
	String hello;

	@ViewById
	TextView helloView;

	@AfterViews
	void initViews() {
		Date now = new Date();
		String helloMessage = String.format(hello, now.toString());
        helloView.setText("abcd123");
        dbtest();
	}

    private void dbtest() {

        //init db.It's only used when init context.
        initDB();

        //insert data
        initData();
//        update();
//        //query
//        query();


        List<Account> accounts = AccountManager.getInstance().findAll();
        for (Account account : accounts)
            Log.i(LOG_TAG,"id="+account.getId()+";name="+account.getName());

        accounts = AccountManager.getInstance().findPage(0,2,"id",false);
        for (Account account : accounts)
            Log.i(LOG_TAG,"id="+account.getId()+";name="+account.getName());
    }

    private void update() {
        Account account = AccountManager.getInstance().findForId(1);
        account.setName("account1 after update");
        AccountManager.getInstance().update(account);

    }

    private void query() {

        List<Order> orders = OrderManager.getInstance().findAll();
        for(Order o:orders){
            Log.i(LOG_TAG,o.getName()+";"+o.getAccount().getName());
        }

        List<Account> accounts = AccountManager.getInstance().findAll();
        for(Account a:accounts){
            Log.i(LOG_TAG,"======"+a.getName()+"======");
            if(a.getOrders()==null)continue;
            CloseableIterable<Order> closeableIterable = a.getOrders().getWrappedIterable();
            Iterator<Order> orderIterator = closeableIterable.iterator();
            while (orderIterator.hasNext()){
                Order o = orderIterator.next();
                Log.i(LOG_TAG,o.getName());
            }
            closeableIterable.closeableIterator();
        }


//        Order qOrder = new Order();
//        qOrder.setName("order");
//        try {
//            List<Order> oos = orderDao.queryBuilder().where().like("name", "%order%").query();
//            Log.i(LOG_TAG,"*****11"+oos.get(0).getName()+"*****");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    void initData(){
        Log.i(LOG_TAG,"method initData() called.");
        Order order = new Order();
        order.setName("order1");
        order.setDate(new Date());
        Account account = new Account();
        account.setId(1);
        order.setAccount(account);
        OrderManager.getInstance().saveOrUpdate(order);
//
//        order = new Order();
//        order.setName("order2");
//        account.setId(1);
//        order.setAccount(account);
//        orderDao.create(order);

    }

    void initDB(){
        createDB("abcd321");
    }

    @Click()
    void helloViewClicked(){
        helloView.setText("good click");
    }


}
