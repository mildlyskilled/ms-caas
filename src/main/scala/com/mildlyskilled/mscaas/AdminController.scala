package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.routes.DbRoutes

class AdminController extends MsCaasStack with DbRoutes {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

}
