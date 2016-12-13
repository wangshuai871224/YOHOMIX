package com.example.dllo.yohomix.tools;

import com.example.dllo.yohomix.bean.CollectWebBean;
import com.example.dllo.yohomix.bean.CollectWebBeanDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/12/13.
 */

public class CommonDBTool {

    private static CommonDBTool tool = new CommonDBTool();
    private static CollectWebBeanDao mDao;

    private CommonDBTool() {
    }

    public static CommonDBTool getInstance() {
        if (tool == null) {
            synchronized (DBTool.class) {
                if (tool == null) {
                    tool = new CommonDBTool();
                }
            }

        }
        mDao = MyApp.getDaoSession().getCollectWebBeanDao();
        return tool;
    }

    public void insertWebBean(final CollectWebBean bean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                QueryBuilder<CollectWebBean> queryBuilder = mDao.queryBuilder().where(CollectWebBeanDao.Properties.Title.eq(bean.getTitle()));
                Long count = queryBuilder.buildCount().count();
                if (count > 0) {
                    return;
                }
                mDao.insert(bean);
            }
        }).start();
    }

    /**
     * 增加集合的方法
     *
     * @param list
     */
    public void insertList(List<CollectWebBean> list) {
        mDao.insertInTx(list);
    }

    /**
     * 删除方法
     */
    /**
     * 删除单一对象
     *
     * @param bean
     */
    public void deleteCollectWebBean(CollectWebBean bean) {
        mDao.delete(bean);
    }


    public void deleteCollectWebBean(Long id) {
        mDao.deleteByKey(id);
    }
    /**
     * 删除所有内容
     */
    public void deleteAll() {
        mDao.deleteAll();
    }

    /**
     * 根据Id删
     */
    public void deleteById(Long id) {
        mDao.deleteByKey(id);
    }

    /**
     * 根据某一个字段进行删除操作
     */
    public void deleteByTitle(String name) {
        DeleteQuery<CollectWebBean> deleteQuery = mDao.queryBuilder().where(CollectWebBeanDao.Properties.Title.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    /**
     * 根据性别姓名年龄进行删除
     *
     * @param name
     * @param sex
     * @param age
     */
    public void deleteBy(String name, String sex, String age) {
        DeleteQuery<CollectWebBean> deleteQuery = mDao.queryBuilder().where(CollectWebBeanDao.Properties.ImgUrl.eq(name)
                , CollectWebBeanDao.Properties.TagName.eq(sex)
                , CollectWebBeanDao.Properties.Title.eq(age)).buildDelete();
        if (deleteQuery != null) {
            deleteQuery.executeDeleteWithoutDetachingEntities();


        }
    }

    /**
     * 查询所有的方法
     */
    public List<CollectWebBean> queryAll() {
        /**
         * 下面两种方法都可以
         */
        List<CollectWebBean> lists = mDao.loadAll();
        List<CollectWebBean> CollectWebBeanList = mDao.queryBuilder().list();
        return lists;

    }

    /**
     * 查重的方法
     */
    //根据姓名来查询
    public boolean isHaveTitle(String title) {
        /**
         * 属性:Properties
         * PersonDao.Properties.Name.eq(name)判断是否含有该属性
         *
         */
        QueryBuilder<CollectWebBean> queryBuilder = mDao.queryBuilder().where(CollectWebBeanDao.Properties.Title.eq(title));
        /**
         * 获取到我们要查询的内容的size
         */
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }

    public boolean isSave(CollectWebBean bean) {
        QueryBuilder<CollectWebBean> queryBuilder = mDao.queryBuilder()
                .where(CollectWebBeanDao.Properties.Title.eq(bean.getTitle())
                        , CollectWebBeanDao.Properties.ImgUrl.eq(bean.getImgUrl())
                        , CollectWebBeanDao.Properties.TagName.eq(bean.getTagName()));
        Long size = queryBuilder.buildCount().count();
        CollectWebBean webBean = queryBuilder.list().get(0);
        return size > 0 ? true : false;
    }


}
