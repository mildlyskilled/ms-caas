package com.mildlyskilled.mscaas.models

import slick.dbio.DBIO
import slick.driver.JdbcDriver.api._
import java.sql.Timestamp

object ApiApplications extends DatabaseConnection {

  case class ApiApplication(id: Int,
                            apiKey: String,
                            appSecret: String,
                            owner: Int,
                            active: Boolean,
                            createdAt: Timestamp,
                            updatedAt: Timestamp
                             )

  class ApiApplications(tag: Tag) extends Table[ApiApplication](tag, "APPS") {
    def id = column[Int]("app_id", O.PrimaryKey)

    def apiKey = column[String]("api_key")

    def appSecret = column[String]("app_secret")

    def ownerId = column[Int]("owner")

    def active = column[Boolean]("active")

    def createdAt = column[Timestamp]("created_at", O.Default(new Timestamp(System.currentTimeMillis)), O.NotNull)

    def updatedAt = column[Timestamp]("updated_at", O.Default(new Timestamp(System.currentTimeMillis)))

    def * = (id, apiKey, appSecret, ownerId, active, createdAt, updatedAt) <>(ApiApplication.tupled, ApiApplication.unapply)

    def owner = foreignKey("OWN_FK", ownerId, Users.users)(_.id)
  }


  lazy val apps = TableQuery[ApiApplications]

  def allApps(activeStatus:Boolean = true) = {

    val registeredApps = {
      for {
        a <- apps
        u <- Users.users if a.ownerId === u.id
      } yield (a, u)
    }

    val action = registeredApps.result
    db.run(action)
  }

  def appsByUser(id:Int) = {
    val action = apps.filter(_.ownerId === id).result
    db.run(action)
  }

  def appById(id:Int) = {
    apps.findBy(_.id === id)
  }

  val buildSchema = apps.schema.create


  val dropSchema = apps.schema.drop

  val currentTime = new Timestamp(System.currentTimeMillis)

  val seedApps = DBIO.seq(
    apps += ApiApplication(1, "apikeyhere", "appsecretehere", 1, true, currentTime, currentTime),
    apps += ApiApplication(2, "apikeytwo", "appsecretetwo", 2, true, currentTime, currentTime),
    apps += ApiApplication(3, "apikeythreeo", "appsecretethree", 1, true, currentTime, currentTime)
  )

  def createAndSeedApps = db.run(DBIO.seq(buildSchema, seedApps))
}
