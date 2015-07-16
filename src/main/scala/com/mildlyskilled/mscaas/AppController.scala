package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.models.ApiApplications


class AppController extends MsCaasStack with CommonJsonController {

  get("/") {
    ApiApplications.allApps()
  }

  get("/user/:id") {
    val userID = params.getAs[Int]("id").getOrElse(halt(400))
    ApiApplications.appsByUser(userID)
  }

  get("/:id") {
    val appID = params.getAs[Int]("id").getOrElse(halt(400))
    ApiApplications.appById(appID)
  }
}
