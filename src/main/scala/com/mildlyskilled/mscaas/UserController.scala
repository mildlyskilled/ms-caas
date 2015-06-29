package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.routes.UserRoutes
import org.scalatra.FutureSupport
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class UserController extends MsCaasStack with UserRoutes with NativeJsonSupport with FutureSupport{
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global
  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }
}
