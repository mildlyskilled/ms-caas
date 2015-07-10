package com.mildlyskilled.mscaas

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.FutureSupport
import org.scalatra.json.JacksonJsonSupport

class AppController extends MsCaasStack
 {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit lazy val jsonFormats: Formats = DefaultFormats
}