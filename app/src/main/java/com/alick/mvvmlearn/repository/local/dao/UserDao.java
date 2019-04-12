package com.alick.mvvmlearn.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.alick.mvvmlearn.model.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
// cache need update
    Long add(User user);

    @Query("select * from user where login = :username")
    User queryByUsername(String username);
}