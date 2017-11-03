package com.jacksonueda.movist.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.jacksonueda.movist.data.local.DatabaseContract
import java.io.Serializable

/**
 * Created by Jackson on 28/10/17.
 */
@Entity(tableName = DatabaseContract.Genres.TABLE_NAME)
class Genre() : Serializable {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = DatabaseContract.Genres.COLUMN_ID)
    var id: Int = 0

    @ColumnInfo(name = DatabaseContract.Genres.COLUMN_NAME)
    var name: String = ""

    constructor(id: Int, name: String) : this() {
        this.id = id
        this.name = name
    }
}