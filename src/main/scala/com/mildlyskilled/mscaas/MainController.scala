package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.models.Tables
import org.scalatra.{FutureSupport, ScalatraBase}
import slick.driver.JdbcDriver.api._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class MainController(val db: Database) extends MsCaasStack with ScalatraBase with FutureSupport with NativeJsonSupport {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global


  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    Map("name" -> "Mildly Skilled Content As A Service", "version" -> "")
  }
}
