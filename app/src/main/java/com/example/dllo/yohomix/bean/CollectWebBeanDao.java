package com.example.dllo.yohomix.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COLLECT_WEB_BEAN".
*/
public class CollectWebBeanDao extends AbstractDao<CollectWebBean, Long> {

    public static final String TABLENAME = "COLLECT_WEB_BEAN";

    /**
     * Properties of entity CollectWebBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ImgUrl = new Property(1, String.class, "imgUrl", false, "IMG_URL");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property WebUrl = new Property(3, String.class, "webUrl", false, "WEB_URL");
        public final static Property Time = new Property(4, String.class, "time", false, "TIME");
        public final static Property TagName = new Property(5, String.class, "tagName", false, "TAG_NAME");
        public final static Property VideoUrl = new Property(6, String.class, "videoUrl", false, "VIDEO_URL");
        public final static Property Type = new Property(7, int.class, "type", false, "TYPE");
    }


    public CollectWebBeanDao(DaoConfig config) {
        super(config);
    }
    
    public CollectWebBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COLLECT_WEB_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"IMG_URL\" TEXT," + // 1: imgUrl
                "\"TITLE\" TEXT," + // 2: title
                "\"WEB_URL\" TEXT," + // 3: webUrl
                "\"TIME\" TEXT," + // 4: time
                "\"TAG_NAME\" TEXT," + // 5: tagName
                "\"VIDEO_URL\" TEXT," + // 6: videoUrl
                "\"TYPE\" INTEGER NOT NULL );"); // 7: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COLLECT_WEB_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CollectWebBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String imgUrl = entity.getImgUrl();
        if (imgUrl != null) {
            stmt.bindString(2, imgUrl);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String webUrl = entity.getWebUrl();
        if (webUrl != null) {
            stmt.bindString(4, webUrl);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String tagName = entity.getTagName();
        if (tagName != null) {
            stmt.bindString(6, tagName);
        }
 
        String videoUrl = entity.getVideoUrl();
        if (videoUrl != null) {
            stmt.bindString(7, videoUrl);
        }
        stmt.bindLong(8, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CollectWebBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String imgUrl = entity.getImgUrl();
        if (imgUrl != null) {
            stmt.bindString(2, imgUrl);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String webUrl = entity.getWebUrl();
        if (webUrl != null) {
            stmt.bindString(4, webUrl);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String tagName = entity.getTagName();
        if (tagName != null) {
            stmt.bindString(6, tagName);
        }
 
        String videoUrl = entity.getVideoUrl();
        if (videoUrl != null) {
            stmt.bindString(7, videoUrl);
        }
        stmt.bindLong(8, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CollectWebBean readEntity(Cursor cursor, int offset) {
        CollectWebBean entity = new CollectWebBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // imgUrl
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // webUrl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // tagName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // videoUrl
            cursor.getInt(offset + 7) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CollectWebBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setImgUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setWebUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTagName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setVideoUrl(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setType(cursor.getInt(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CollectWebBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CollectWebBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CollectWebBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
