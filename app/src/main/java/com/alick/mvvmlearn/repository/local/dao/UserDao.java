package com.alick.mvvmlearn.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.alick.mvvmlearn.model.Account;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
// cache need update
    Long add(Account account);

    @Query("select * from Account where login = :username")
    Account queryByUsername(String username);
}