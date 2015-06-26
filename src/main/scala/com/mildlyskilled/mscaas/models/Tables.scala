package com.mildlyskilled.mscaas.models

import slick.driver.JdbcDriver.api._
import java.sql.Timestamp


object Tables {

  class Users(tag: Tag)
    extends Table[(Int, String, String, String, String)](tag, "USERS") {

    def id = column[Int]("user_id", O.PrimaryKey)

    def firstName = column[String]("first_name")

    def lastName = column[String]("last_name")

    def email = column[String]("email")

    def password = column[String]("password")

    def createdAt = column[Timestamp]("created_at", O.Default(new Timestamp(System.currentTimeMillis)), O.NotNull)

    def updatedAt = column[Timestamp]("updated_at", O.Default(new Timestamp(System.currentTimeMillis)))

    // projection
    def * = (id, firstName, lastName, email, password)

  }


  class ContentMeta(tag: Tag) extends Table[(Int, String, String, String, Int)](tag, "CONTENTMETA") {

    def id = column[Int]("meta_id", O.PrimaryKey)

    def contentType = column[String]("content_type")

    def path = column[String]("content_path")

    def extra = column[String]("extra")

    def userId = column[Int]("user_id")

    def * = (id, contentType, path, extra, userId)

    def createdBy = foreignKey("USER_FK", userId, users)(_.id)

  }


  class Content(tag: Tag) extends Table[(Int, String, Timestamp, Timestamp)](tag, "CONTENT") {
    def id = column[Int]("content_id", O.PrimaryKey)

    def contents = column[String]("content_id")

    def createdAt = column[Timestamp]("created_at")

    def updatedAt = column[Timestamp]("updated_at")

    def * = (id, contents, createdAt, updatedAt)

  }

  val users = TableQuery[Users]

  val contentMeta = TableQuery[ContentMeta]

  val buildSchema = (users.schema ++ contentMeta.schema).create

  val dropSchema = (users.schema ++ contentMeta.schema).drop

  val seedUsersAndMeta = DBIO.seq(
    Tables.users += (1, "Kwabena", "Aning", "kwabena.aning@gmail.com", "changeme"),
    Tables.users += (2, "Kobby", "Aning", "kwabena.aning@outlook.com", "changeme"),
    Tables.contentMeta ++= Seq(
      (1, "Text", "None", "{json: \"content here\"}", 1),
      (2, "HTML", "None", "{json: \"content here\"}", 1)
    )
  )

  val createDatabase = DBIO.seq(buildSchema, seedUsersAndMeta)

  val allUsers = {
    for {u <- users} yield (u.id, u.firstName, u.lastName, u.email)
  }

}
