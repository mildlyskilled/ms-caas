package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.routes.UserRoutes
import slick.driver.JdbcDriver.api._

class UserController(val db: Database) extends MsCaasStack with UserRoutes{
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global
}
