package aut.bme.hu.boltie.model

import androidx.room.*

@Entity(tableName = "sessions")
class PurchaseSession(
    @PrimaryKey(autoGenerate = true) val id: Long
) { }

@Entity(tableName = "sessionElements")
class PurchaseSessionElement(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "productId") val productId: Long,
    @ColumnInfo(name = "price") val price: Float
) { }