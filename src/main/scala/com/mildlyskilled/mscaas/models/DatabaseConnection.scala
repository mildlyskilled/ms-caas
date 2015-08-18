package com.mildlyskilled.mscaas.models

import com.mchange.v2.c3p0.ComboPooledDataSource
import slick.driver.H2Driver.api._

trait DatabaseConnection {
  val cpds = new ComboPooledDataSource
  val db = Database.forDataSource(cpds)

}
