import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

object Main extends App {
  override def main(args: Array[String]): Unit = {}

  @JSExportTopLevel("createStore")
  def createStore(reducer: js.Function2[js.Object, js.Object, js.Object]) =
    new Store(reducer)
}
