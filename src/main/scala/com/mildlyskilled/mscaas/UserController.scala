package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.models.Users
import org.json4s.JsonAST.{JField, JValue}




class UserController extends MsCaasStack
with CommonJsonController {

  protected val applicationDescription = "The user API exposes methods to interact with user accounts for this service"

  protected override def transformResponseBody(body: JValue) = {
    body.underscoreKeys.removeField {
      case JField("password", _) => true
      case _ => false
    }
  }


  get("/") {
    Users.allUsers
  }

  get("/:id") {
    val userID = params.getAs[Int]("id").getOrElse(halt(400))
    Users.findUserByID(userID)
  }
}
