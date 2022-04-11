package aut.bme.hu.boltie.model

enum class UserType {
    Restocker, // TODO: Actual english name
    Seller,
    Manager
}

class User(
    val type: UserType,
    val name: String,
    val id: String
) {

}