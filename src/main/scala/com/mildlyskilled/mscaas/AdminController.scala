package com.mildlyskilled.mscaas

import slick.driver.JdbcDriver.api._
import com.mildlyskilled.mscaas.routes.DbRoutes

class AdminController(val db: Database) extends MsCaasStack with DbRoutes{

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

}
