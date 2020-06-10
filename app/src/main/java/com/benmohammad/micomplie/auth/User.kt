package com.benmohammad.micomplie.auth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_user")
class User(
    @PrimaryKey
    var username: String,
    var password: String
)