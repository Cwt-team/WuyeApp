// com/example/wuye_app/data/local/UserDao.java
package com.example.wuye_app.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wuye_app.data.model.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE userId = :userId") // Use userId to match the User entity
    User getUserById(String userId); // Changed parameter type to String
}
