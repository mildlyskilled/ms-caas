package com.mildlyskilled.mscaas.models

import slick.dbio.DBIO
import slick.driver.H2Driver.api._
import java.sql.Timestamp

object Users extends DatabaseConnection {

  case class User(id: Int,
                  firstName: String,
                  lastName: String,
                  email: String,
                  password: String,
                  createdAt: Timestamp,
                  updatedAt: Timestamp)

  class Users(tag: Tag)
    extends Table[User](tag, "USERS") {

    def id = column[Int]("user_id", O.PrimaryKey)

    def firstName = column[String]("first_name")

    def lastName = column[String]("last_name")

    def email = column[String]("email")

    def password = column[String]("password")

    def createdAt = column[Timestamp]("created_at", O.Default(new Timestamp(System.currentTimeMillis)))

    def updatedAt = column[Timestamp]("updated_at", O.Default(new Timestamp(System.currentTimeMillis)))

    // projection
    def * = (id, firstName, lastName, email, password, createdAt, updatedAt) <> (User.tupled, User.unapply)

  }

  object UserQuery extends TableQuery(new Users(_)){
    val findById = this.findBy(_.id)

    val findByEmail = this.findBy(_.email)
  }

  lazy val users = TableQuery[Users]

  def allUsers = {
    db.run(users.result)
  }


  def findUserByID(userID: Int) = {
    val user = users.filter(_.id === userID).result
    db.run(user)
  }

  val buildSchema = users.schema.create

  val dropSchema = users.schema.drop

  val currentTime = new Timestamp(System.currentTimeMillis)

  val seedUsers = DBIO.seq(
    users += User(1, "Kwabena", "Aning", "kwabena.aning@gmail.com", "changeme", currentTime, currentTime),
    users += User(2, "Kobby", "Aning", "kwabena.aning@outlook.com", "changeme", currentTime, currentTime))

  def createAndSeedUsers = db.run(DBIO.seq(buildSchema, seedUsers))
}
