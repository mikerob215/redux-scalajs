import com.redux.Store.StoreLiteral
import com.redux.{Action, State, Store}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

object Main extends App {
  override def main(args: Array[String]): Unit = {}

  /**
    *
    * @param reducer - Reducer function
    * @return
    */
  @JSExportTopLevel("createStore")
  def createStore(reducer: js.Function2[State, Action, State]): StoreLiteral = {
    Store(reducer)
  }
}
