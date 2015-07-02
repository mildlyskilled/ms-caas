package com.mildlyskilled.mscaas

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.{JacksonSwaggerBase, ApiInfo, Swagger}


class ResourcesController(implicit val swagger: Swagger) extends ScalatraServlet with JacksonSwaggerBase


object UserAPIInfo extends ApiInfo(
  "The Mildly Skilled User API",
  "Docs for the User API",
  "http://mildlyskilled.com",
  "kwabena.aning@gmail.com",
  "MIT",
  "http://opensource.org/licenses/MIT")

class UserSwagger extends Swagger(Swagger.SpecVersion, "0.1.0-SNAPSHOT", UserAPIInfo)

