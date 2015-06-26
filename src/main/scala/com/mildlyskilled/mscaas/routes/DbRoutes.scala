package com.mildlyskilled.mscaas.routes

import com.mildlyskilled.mscaas.models.Tables
import org.scalatra.{ScalatraBase, FutureSupport}
import slick.driver.JdbcDriver.api._

trait DbRoutes extends ScalatraBase with FutureSupport{

  def db: Database

  before(){
    contentType = "text/html"
  }

  get("/create-tables") {
    db.run(Tables.createDatabase)
    "Tables Created<br /><a href=\"/admin/seed-data\">Seed Data</a>"
  }

  get("/seed-data") {
    db.run(Tables.seedUsersAndMeta)
    "Data Seeded <a href=\"/users\">View Users</a>"
  }

}
