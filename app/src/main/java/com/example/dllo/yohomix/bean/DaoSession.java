package com.example.dllo.yohomix.bean;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.dllo.yohomix.bean.CollectWebBean;
import com.example.dllo.yohomix.bean.ColumnCollectBean;
import com.example.dllo.yohomix.bean.SearchItemBean;

import com.example.dllo.yohomix.bean.CollectWebBeanDao;
import com.example.dllo.yohomix.bean.ColumnCollectBeanDao;
import com.example.dllo.yohomix.bean.SearchItemBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig collectWebBeanDaoConfig;
    private final DaoConfig columnCollectBeanDaoConfig;
    private final DaoConfig searchItemBeanDaoConfig;

    private final CollectWebBeanDao collectWebBeanDao;
    private final ColumnCollectBeanDao columnCollectBeanDao;
    private final SearchItemBeanDao searchItemBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        collectWebBeanDaoConfig = daoConfigMap.get(CollectWebBeanDao.class).clone();
        collectWebBeanDaoConfig.initIdentityScope(type);

        columnCollectBeanDaoConfig = daoConfigMap.get(ColumnCollectBeanDao.class).clone();
        columnCollectBeanDaoConfig.initIdentityScope(type);

        searchItemBeanDaoConfig = daoConfigMap.get(SearchItemBeanDao.class).clone();
        searchItemBeanDaoConfig.initIdentityScope(type);

        collectWebBeanDao = new CollectWebBeanDao(collectWebBeanDaoConfig, this);
        columnCollectBeanDao = new ColumnCollectBeanDao(columnCollectBeanDaoConfig, this);
        searchItemBeanDao = new SearchItemBeanDao(searchItemBeanDaoConfig, this);

        registerDao(CollectWebBean.class, collectWebBeanDao);
        registerDao(ColumnCollectBean.class, columnCollectBeanDao);
        registerDao(SearchItemBean.class, searchItemBeanDao);
    }
    
    public void clear() {
        collectWebBeanDaoConfig.clearIdentityScope();
        columnCollectBeanDaoConfig.clearIdentityScope();
        searchItemBeanDaoConfig.clearIdentityScope();
    }

    public CollectWebBeanDao getCollectWebBeanDao() {
        return collectWebBeanDao;
    }

    public ColumnCollectBeanDao getColumnCollectBeanDao() {
        return columnCollectBeanDao;
    }

    public SearchItemBeanDao getSearchItemBeanDao() {
        return searchItemBeanDao;
    }

}
