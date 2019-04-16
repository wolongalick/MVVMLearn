package com.alick.mvvmlearn.repository.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alick.mvvmlearn.model.Account;
import com.alick.mvvmlearn.repository.local.dao.UserDao;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {
    public abstract UserDao getUserDao();

}