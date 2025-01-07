// com/example/wuye_app/data/local/AppDatabase.java
package com.example.wuye_app.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.wuye_app.data.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false) // Added exportSchema = false
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    // 可以添加其他 DAO
}
