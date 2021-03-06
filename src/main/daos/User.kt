package main.daos

import framework.models.BaseIntEntity
import framework.models.BaseIntEntityClass
import framework.models.BaseIntIdTable
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.Table

/**
 * Basic fields that a User needs
 * @property id User id
 * @property email User email
 * @property firstname User first name
 * @property lastname User last name
 * @property metadata Metadata used to track additional information about the user
 */
class User(id: EntityID<Int>) : BaseIntEntity(id, Users) {
	companion object : BaseIntEntityClass<User>(Users)

	var email by Users.email
	var firstname by Users.firstname
	var lastname by Users.lastname
	var metadatas by Metadata via UsersMetadata
}

object Users : BaseIntIdTable("users") {
	val email = varchar("email", 50).uniqueIndex()
	val firstname = varchar("firstname", 20)
	val lastname = varchar("lastname", 20)
}

object UsersMetadata : Table("users_to_metadatas") {
	val user = reference("user_to_metadatas", Users).primaryKey(0)
	val metadata = reference("metadata_to_transaction", Metadatas).primaryKey(1)
}