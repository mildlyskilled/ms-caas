package com.mildlyskilled.mscaas

import com.mildlyskilled.mscaas.models.Tables
import org.scalatra.{FutureSupport, ScalatraBase}
import slick.driver.JdbcDriver.api._

class MainController(val db: Database) extends MsCaasStack with ScalatraBase with FutureSupport {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say
        <a href="hello-scalate">hello to Scalate</a>
        .
      </body>
    </html>
  }

  get("/users") {
    <html>
      <body>
        Users
      </body>
    </html>

    db.run(Tables.allUsers.result) map { xs =>
      contentType = "text/Plain"
      xs map {
        case (fname, lname, email) => f" $fname $lname - $email"
      } mkString "\n"
    }
  }


}
