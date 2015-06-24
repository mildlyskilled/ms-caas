package com.mildlyskilled.mscaas.routes

import com.mildlyskilled.mscaas.models.Tables
import org.scalatra.{ScalatraBase, FutureSupport}
import slick.driver.JdbcDriver.api._

trait DbRoutes extends ScalatraBase with FutureSupport{

  def db: Database


  get("/create-tables") {
    db.run(Tables.createDatabase)
  }

  get("/seed-data") {
    db.run(Tables.seedUsersAndMeta)
  }

}
