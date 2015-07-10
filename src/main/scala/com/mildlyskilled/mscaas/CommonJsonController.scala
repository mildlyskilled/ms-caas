package com.mildlyskilled.mscaas

import org.scalatra.FutureSupport
import org.scalatra.json.JacksonJsonSupport


trait CommonJsonController extends JacksonJsonSupport
with FutureSupport{

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  before() {
    contentType = formats("json")
  }
}
