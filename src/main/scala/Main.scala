import com.redux._

import scala.scalajs.js.annotation.JSExportTopLevel

object Main extends App {
  override def main(args: Array[String]): Unit = {}

  /**
    *
    * @param reducer - Reducer function
    * @return
    */
  @JSExportTopLevel("createStore")
  def createStore(reducer: ReducerFunction): StoreLiteral = {
    Store(reducer)
  }
}
