package com.mildlyskilled.mscaas

import org.scalatra.{FutureSupport, ScalatraBase}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class MainController extends MsCaasStack with ScalatraBase with FutureSupport with JacksonJsonSupport {

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
