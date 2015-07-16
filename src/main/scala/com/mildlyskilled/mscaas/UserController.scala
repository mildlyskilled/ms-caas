package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.models.Users

class UserController extends MsCaasStack with CommonJsonController {

  protected val applicationDescription = "The user API exposes methods to interact with user accounts for this service"

  get("/") {
    Users.allUsers
  }

  get("/:id") {
    val userID = params.getAs[Int]("id").getOrElse(halt(400))
    Users.findUserByID(userID)
  }
}
