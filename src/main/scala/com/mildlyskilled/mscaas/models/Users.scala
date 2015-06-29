package com.mildlyskilled.mscaas.models

import slick.dbio.DBIO
import slick.driver.JdbcDriver.api._
import java.sql.Timestamp

case class User(id: Int,
                firstName: String,
                lastName: String,
                email: String,
                password: String,
                createdAt: Timestamp,
                updatedAt: Timestamp)

object Users extends DatabaseConnection {


  class Users(tag: Tag)
    extends Table[User](tag, "USERS") {


    def id = column[Int]("user_id", O.PrimaryKey)

    def firstName = column[String]("first_name")

    def lastName = column[String]("last_name")

    def email = column[String]("email")

    def password = column[String]("password")

    def createdAt = column[Timestamp]("created_at", O.Default(new Timestamp(System.currentTimeMillis)), O.NotNull)

    def updatedAt = column[Timestamp]("updated_at", O.Default(new Timestamp(System.currentTimeMillis)))

    // projection
    def * = (id, firstName, lastName, email, password, createdAt, updatedAt) <>(User.tupled, User.unapply)

  }

  val users = TableQuery[Users]

  def allUsers = {
    val us = users.result
    db.run(us)
  }

  def findUserByID(userID: Int) = {
    val user = users.filter(u => u.id === userID).result
    db.run(user)
  }

  val buildSchema = users.schema.create

  val dropSchema = users.schema.drop

  val currentTime = new Timestamp(System.currentTimeMillis)

  val seedUsers = DBIO.seq(
    users += User(1, "Kwabena", "Aning", "kwabena.aning@gmail.com", "changeme", currentTime, currentTime),
    users += User(2, "Kobby", "Aning", "kwabena.aning@outlook.com", "changeme", currentTime, currentTime)
  )

  val createDatabase = DBIO.seq(buildSchema, seedUsers)
}
