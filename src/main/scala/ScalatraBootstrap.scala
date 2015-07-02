import com.mildlyskilled.mscaas._
import org.scalatra._
import javax.servlet.ServletContext
import org.slf4j.{Logger, LoggerFactory}

class ScalatraBootstrap extends LifeCycle {

  val logger = LoggerFactory.getLogger(getClass)
  logger.info("Application bootstrapped")


  implicit val swagger = new UserSwagger

  override def init(context: ServletContext) {

    context.mount(new MainController, "/*")
    context.mount(new AdminController, "/admin/*")
    context.mount(new UserController, "/users", "users")
    context.mount(new ResourcesController, "/api-docs")
  }
}
