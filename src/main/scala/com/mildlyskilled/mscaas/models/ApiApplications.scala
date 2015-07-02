package com.mildlyskilled.mscaas.models

import java.sql.Timestamp

import slick.dbio.DBIO
import slick.driver.JdbcDriver.api._
import java.sql.Timestamp

object ApiApplications extends DatabaseConnection {

  case class ApiApplication(apiKey: String,
                         appSecret: String,
                         owner: Int,
                         active: Boolean,
                         createdAt: Timestamp,
                         updatedAt: Timestamp
                          )

  class ApiApplications(tag: Tag) extends Table[ApiApplication](tag, "APPS"){
    def id = column[Int]("app_id", O.PrimaryKey)
    def apiKey = column[String]("api_key")
    def appSecret = column[String]("app_secret")
    def ownerId = column[Int]("owner")
    def active = column[Boolean]("active")
    def createdAt = column[Timestamp]("created_at", O.Default(new Timestamp(System.currentTimeMillis)), O.NotNull)
    def updatedAt = column[Timestamp]("updated_at", O.Default(new Timestamp(System.currentTimeMillis)))

    def * = (id, apiKey, appSecret, active, createdAt, updatedAt) <> (ApiApplication.tupled, ApiApplication.unapply)

    def owner = foreignKey("OWN_FK", ownerId, Users.users)
  }


  lazy val apps = TableQuery[ApiApplications]

  def allApps(activeStatus:Boolean = true) = {
    db.run(apps.filter(_.active === activeStatus).result)
  }
}
