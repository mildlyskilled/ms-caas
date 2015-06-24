import com.mchange.v2.c3p0.ComboPooledDataSource
import com.mildlyskilled.mscaas._
import org.scalatra._
import javax.servlet.ServletContext
import slick.driver.JdbcDriver.api._
import org.slf4j.LoggerFactory

class ScalatraBootstrap extends LifeCycle {

  val logger = LoggerFactory.getLogger(getClass)

  val cpds = new ComboPooledDataSource

  logger.info("Created C3P0 connection pool")

  override def init(context: ServletContext) {
    val db = Database.forDataSource(cpds)
    context.mount(new MainController(db), "/*")
    context.mount(new AdminController(db), "/admin/*")
  }
}
