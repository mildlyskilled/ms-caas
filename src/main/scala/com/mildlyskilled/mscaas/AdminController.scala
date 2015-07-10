package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.models.{ApiApplications, Users}

class AdminController extends MsCaasStack {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  before() {
    contentType = "text/html"
  }

  get("/create-seed") {
    Users.createAndSeedDatabase
    ApiApplications.createAndSeedApps
    "Tables Created and Seeded<br /><a href=\"/users\">View Users</a> " +
      "<a href=\"/apps\">View Apps</a>"
  }


}
