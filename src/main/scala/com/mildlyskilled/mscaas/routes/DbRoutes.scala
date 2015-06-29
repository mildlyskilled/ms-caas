package com.mildlyskilled.mscaas.routes

import com.mildlyskilled.mscaas.models.Users
import org.scalatra.{ScalatraBase, FutureSupport}

trait DbRoutes extends ScalatraBase with FutureSupport {
  before(){
    contentType = "text/html"
  }

  get("/create-seed") {
   Users.createAndSeedDatabase
    "Tables Created and Seeded<br /><a href=\"/users\">Seed Data</a>"
  }

}
