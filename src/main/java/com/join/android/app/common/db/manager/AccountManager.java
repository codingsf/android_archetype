package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.join.android.app.common.db.tables.Account;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 上午11:01
 */

public class AccountManager extends BaseManager<Account> {

    private static AccountManager accountManager;
    private static RuntimeExceptionDao<Account, Integer> dao;

    private AccountManager() {
        super(dao);
    }

    public static AccountManager getInstance() {
        if (accountManager == null) {
            dao = DBManager.getInstance(null).getHelper().getAccountDao();
            accountManager = new AccountManager();
        }
        return accountManager;
    }

}
