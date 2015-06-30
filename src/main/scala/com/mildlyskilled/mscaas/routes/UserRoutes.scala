package com.mildlyskilled.mscaas.routes

import com.mildlyskilled.mscaas.models.{Users, User}
import org.scalatra.{FutureSupport, ScalatraBase}

trait UserRoutes extends ScalatraBase with FutureSupport {

  get("/") {
    Users.allUsers
  }

  get("/:id") {
    val userID = params.getAs[Int]("id").getOrElse(halt(400))
    Users.findUserByID(userID)
  }
}
