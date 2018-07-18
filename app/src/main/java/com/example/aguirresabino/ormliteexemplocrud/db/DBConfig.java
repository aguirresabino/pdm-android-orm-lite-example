package com.example.aguirresabino.ormliteexemplocrud.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.aguirresabino.ormliteexemplocrud.model.LivroModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBConfig extends OrmLiteSqliteOpenHelper {

    private static final String DB_NOME = "exemploCrud";
    private static final int DB_VERSAO = 1;
    private Dao<LivroModel, Integer> livroDao;

    public DBConfig(Context context) {
        super(context,DB_NOME, null, DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, LivroModel.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, LivroModel.class, true);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
    }

    public Dao<LivroModel, Integer> getLivroDao() throws SQLException,
            java.sql.SQLException {
        if (livroDao == null) {
            livroDao = getDao(LivroModel.class);
        }
        return livroDao;
    }
}
