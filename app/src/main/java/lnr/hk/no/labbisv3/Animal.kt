package lnr.hk.no.labbisv3

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "animal_table")
data class Animal(@PrimaryKey @ColumnInfo (name = "animal")val animal: String)