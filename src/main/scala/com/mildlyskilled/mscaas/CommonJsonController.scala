package com.mildlyskilled.mscaas

import org.json4s.JsonAST.{JField, JValue}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.FutureSupport
import org.scalatra.json.JacksonJsonSupport


trait CommonJsonController extends JacksonJsonSupport with FutureSupport{

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
}
