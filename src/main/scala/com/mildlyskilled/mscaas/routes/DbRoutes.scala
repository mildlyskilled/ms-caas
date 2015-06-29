package com.mildlyskilled.mscaas.routes

import com.mildlyskilled.mscaas.models.{DatabaseConnection, Users}
import org.scalatra.{ScalatraBase, FutureSupport}
import slick.driver.JdbcDriver.api._

trait DbRoutes extends ScalatraBase with FutureSupport with DatabaseConnection{

  def db: Database

  before(){
    contentType = "text/html"
  }

  get("/create-tables") {
    db.run(Users.createDatabase)
    "Tables Created<br /><a href=\"/admin/seed-data\">Seed Data</a>"
  }

  get("/seed-data") {
    db.run(Users.seedUsers)
    "Data Seeded <a href=\"/users\">View Users</a>"
  }

}
