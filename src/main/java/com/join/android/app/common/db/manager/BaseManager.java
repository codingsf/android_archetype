package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 下午1:31
 */
public class BaseManager<E> {
    private RuntimeExceptionDao dao;


    public BaseManager(RuntimeExceptionDao dao) {
        this.dao = dao;
    }

    public int save(E data) {
        return dao.create(data);
    }

    public E saveIfNotExists(E data) {
        return (E) dao.createIfNotExists(data);
    }

    public E saveOrUpdate(E data) {
        return (E) dao.createOrUpdate(data);
    }

    public int delete(E data) {
        return dao.delete(data);
    }

    public int delete(Collection datas) {
        return dao.delete(datas);
    }

    public int deleteById(Object id) {
        return dao.deleteById(id);
    }

    public int deleteByIds(Collection ids) {
        return dao.deleteIds(ids);
    }

    public int update(E data) {
        return dao.update(data);
    }

    public List<E> findAll() {
        return dao.queryForAll();

    }

    public E findForId(Object id) {
        return (E) dao.queryForId(id);

    }

    public List<E> findForParams(Map<String, Object> params) {
        return dao.queryForFieldValues(params);
    }


    /**
     * @param start     begin from 0
     * @param size      the size of per page
     * @param orderBy   column name
     * @param ascending
     * @return
     */
    public List<E> findPage(long start, long size, String orderBy, boolean ascending) {
        QueryBuilder<E, Integer> builder = dao.queryBuilder();
        try {
            builder.orderBy(orderBy, ascending);
            builder.offset(start);
            builder.limit(size);
            return builder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<E> findPage(Where where,long start, long size, String orderBy, boolean ascending) {
        QueryBuilder<E, Integer> builder = dao.queryBuilder();
        try {
            builder.setWhere(where);
            builder.orderBy(orderBy, ascending);
            builder.offset(start);
            builder.limit(size);
            return builder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int deleteAll(){
        DeleteBuilder<E,Integer> builder = dao.deleteBuilder();
        try {
            return builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
