package com.mildlyskilled.mscaas.routes

import com.mildlyskilled.mscaas.models.{Users}
import org.scalatra.{FutureSupport, ScalatraBase}

trait UserRoutes extends ScalatraBase with FutureSupport {

  get("/") {
    Users.allUsers
  }

  get("/:id") {
    val userID = params.getAs[Int]("id").getOrElse(halt(404))
    Users.findUserByID(userID)
  }
}
