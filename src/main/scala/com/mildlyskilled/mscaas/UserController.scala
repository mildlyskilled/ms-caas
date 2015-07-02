package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.models.Users
import com.mildlyskilled.mscaas.models.Users.User
import org.json4s.JsonAST.{JField, JValue}
import org.scalatra.FutureSupport
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.scalatra.swagger.{SwaggerSupport, Swagger}


class UserController extends MsCaasStack
with JacksonJsonSupport
with FutureSupport {

  protected val applicationDescription = "The user API exposes methods to interact with user accounts for this service"

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  protected override def transformResponseBody(body: JValue) = {
    body.underscoreKeys.removeField {
      case JField("password", _) => true
      case _ => false
    }
  }

  before() {
    contentType = formats("json")
  }

  get("/") {
    Users.allUsers
  }

  get("/:id") {
    val userID = params.getAs[Int]("id").getOrElse(halt(400))
    Users.findUserByID(userID)
  }
}
