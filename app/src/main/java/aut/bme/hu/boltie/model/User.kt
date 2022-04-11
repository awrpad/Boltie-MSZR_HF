package aut.bme.hu.boltie.model

import androidx.room.*

enum class UserType {
    Restocker, // TODO: Actual english name
    Seller,
    Manager
}

@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true) var localId: Long,
    @ColumnInfo(name = "userID") val userId: String,
    @ColumnInfo(name = "userType") val type: UserType,
    @ColumnInfo(name = "name") val name: String
) { }

@Dao
interface UserDAO {
    // TODO: This method
    @Query("""SELECT * FROM user""")
    fun getCurrentUser(): User

    @Insert
    fun insertUsers(vararg users: User)

    @Delete
    fun deleteUser(user: User)
}