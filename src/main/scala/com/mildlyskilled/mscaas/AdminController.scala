package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.models.{ApiApplications, Users}

class AdminController extends MsCaasStack {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  before() {
    contentType = "text/html"
  }

  get("/setup") {
    Users.createAndSeedUsers
    ApiApplications.createAndSeedApps
    "Tables Created and Seeded<br />" +
      "<ul>" +
      "<li><a href=\"/users\">View Users</a></li>" +
      "<li><a href=\"/apps\">View Apps</a></li>" +
      "<ul>"
  }


}
