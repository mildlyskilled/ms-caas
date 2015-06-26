package com.mildlyskilled.mscaas.routes

import com.mildlyskilled.mscaas.models.Tables
import org.scalatra.{FutureSupport, ScalatraBase}
import slick.driver.JdbcDriver.api._

trait UserRoutes extends ScalatraBase with FutureSupport{

  def db: Database

  get("/") {

    db.run(Tables.allUsers.result) map { xs =>
      xs map { case (id, fname, lname, email) =>
        Map("id" -> id, "first_name" -> fname, "last_name" -> lname, "email" -> email)
      }
    }
  }

  get("/:id") {
    db.run(Tables.users.filter(_.id === params("id").toInt).result) map { xs =>
      xs map { case (id, fname, lname, email , password) =>
        Map("id" -> id, "first_name" -> fname, "last_name" -> lname, "email" -> email)
      }
    }
  }
}
