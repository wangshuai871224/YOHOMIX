package com.example.dllo.yohomix.tools;

import android.os.Handler;
import android.os.Looper;

import com.example.dllo.yohomix.bean.DaoMaster;
import com.example.dllo.yohomix.bean.DaoSession;
import com.example.dllo.yohomix.bean.SearchItemBean;
import com.example.dllo.yohomix.bean.SearchItemBeanDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 16/12/6.
 */
public class DBTool {

    private static DBTool mDBTool = new DBTool();
    private SearchItemBeanDao mDao;
    private Executor mThreedPool;
    private Handler mHandler;

    public static DBTool getInstance() {
        return mDBTool;
    }

    private DBTool() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApp.getContext(), "MyDB.db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        mDao = daoSession.getSearchItemBeanDao();
        // 可以在handler的构造方法里传入Looper.getMainLooper的参数
        // 来保证这个Handler一定属于主线程
        mHandler = new Handler(Looper.getMainLooper());
        // cpu数
        int coreNum = Runtime.getRuntime().availableProcessors();
        // 最大线程= 核心线程   任务队列无限大
        mThreedPool = Executors.newFixedThreadPool(coreNum + 1);
    }

    // 插入
    public void insert(final SearchItemBean bean) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                QueryBuilder<SearchItemBean> queryBuilder = mDao.queryBuilder().where(SearchItemBeanDao.Properties.Url.eq(bean.getUrl()));
                Long count = queryBuilder.buildCount().count();
                if (count > 0) {
                    return;
                }
                mDao.insert(bean);
            }
        }).start();
    }

    // 增加集合
    public void insertList(List<SearchItemBean> SearchItemBeans) {
        mDao.insertInTx(SearchItemBeans);
    }

    // 删除方法单一对象的方法
    public void deleteSearchItemBean(SearchItemBean SearchItemBean) {
        mDao.delete(SearchItemBean);
    }

    // 删除所有内容
    public void deleteAll() {
        mDao.deleteAll();
    }

    // 根据Id进行删除
    public void DeleteById(Long id) {
        mDao.deleteByKey(id);
    }

    // 根据某一字段进行删除操作
    public void deleteByName(String name) {
        DeleteQuery<SearchItemBean> deleteQuery = mDao.queryBuilder().where(SearchItemBeanDao.Properties.Url.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    // 根据参数删除
    public void deleteBy(String name, String sex, String age) {
        DeleteQuery<SearchItemBean> deleteQuery = mDao.queryBuilder()
                .where(SearchItemBeanDao.Properties.Body.eq(name)
                        , SearchItemBeanDao.Properties.Count.eq(sex)
                        , SearchItemBeanDao.Properties.Url.eq(age)).buildDelete();
        if (deleteQuery != null) {
            deleteQuery.executeDeleteWithoutDetachingEntities();
        }
    }

    // 查询所有方法
    public List<SearchItemBean> queryAll() {
        List<SearchItemBean> list = mDao.loadAll();
//                         mDao.queryBuilder().list(); 第二种查询方法 与上面的一样效果
        return list;
    }

    // 查重方法, 根据姓名查询
    public boolean isSave(String name) {
        QueryBuilder<SearchItemBean> queryBuilder = mDao.queryBuilder().where(SearchItemBeanDao.Properties.Body.eq(name));
        // 获取到我们要查询的内容的size
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }

    public boolean isSave(SearchItemBean SearchItemBean) {
        QueryBuilder<SearchItemBean> queryBuilder = mDao.queryBuilder().where(SearchItemBeanDao.Properties.Body.eq(SearchItemBean.getBody())
                , SearchItemBeanDao.Properties.Url.eq(SearchItemBean.getUrl())
                , SearchItemBeanDao.Properties.Body.eq(SearchItemBean.getBody()));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }




}
