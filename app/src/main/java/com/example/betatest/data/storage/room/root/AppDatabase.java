package com.example.betatest.data.storage.room.root;

import static com.yandex.runtime.Runtime.getApplicationContext;

import static java.sql.Types.BLOB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.betatest.BitmapTypeConverter;
import com.example.betatest.data.storage.room.dao.ProjectDao;
import com.example.betatest.data.storage.room.dao.SettDao;
import com.example.betatest.data.storage.room.entity.ProjectModel;
import com.example.betatest.data.storage.room.entity.SettModel;

@Database(entities = {ProjectModel.class, SettModel.class}, exportSchema = false, version = 7)
@TypeConverters({BitmapTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "project_database.db";
    public static AppDatabase instance;


    private static final Object LOCK = new Object();

    public abstract ProjectDao projectDao();
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, DATABASE_NAME)
                            .addMigrations(MIGRATION_6_7)
                            .build();
                }
            }
        }
        return instance;
    }


    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE IF NOT EXISTS `new_project` (`pId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `p_title` TEXT, `language` TEXT,  `watcher` INTEGER NOT NULL, `issues` INTEGER NOT NULL, `issues3` INTEGER NOT NULL DEFAULT 6, `name` TEXT, `description` TEXT, `imgUrl` TEXT)");


            database.execSQL("INSERT INTO `new_project` (`pId`, `p_title`, `language`, `watcher`, `issues`, `name`, `description`, `imgUrl`) SELECT `pId`, `p_title`, `language`, `watcher`, `issues`, `description`, `imgUrl` FROM `project`");


            database.execSQL("DROP TABLE IF EXISTS `project`");


            database.execSQL("ALTER TABLE `new_project` RENAME TO `project`");
        }
    };

    static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE IF NOT EXISTS `new_project33` (`jiji2` INTEGER NOT NULL, `joji` INTEGER NOT NULL PRIMARY KEY, `joji2` INTEGER NOT NULL,`jiji` INTEGER NOT NULL)");

            database.execSQL("DROP TABLE IF EXISTS `project2`");

            database.execSQL("ALTER TABLE `new_project33` RENAME TO `project2`");
        }
    };

    public void clearAllData() {
        runInTransaction(new Runnable() {
            @Override
            public void run() {
                projectDao().deleteAllProjects();
            }
        });
    }
}
