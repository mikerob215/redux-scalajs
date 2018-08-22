import scala.collection.mutable.ArrayBuffer
import scala.scalajs.js

class Store(reducer: js.Function2[js.Object, js.Object, js.Object],
            preLoadedState: js.Object)
    extends js.Object
    with StateContainer {
  private var state: js.Object = js.Dynamic.literal()
  private var observers: ArrayBuffer[js.Function0[Unit]] = ArrayBuffer()
  private var isDispatching = false

  def this(reducer: js.Function2[js.Object, js.Object, js.Object]) {
    this(reducer, js.Dynamic.literal())
  }

  dispatch(js.Dynamic.literal(`type` = "INIT"))

  override def dispatch(action: js.Object): js.Object = {
    if (js.typeOf(action) != "object")
      throw js.JavaScriptException("Actions must be plain js objects")

    isDispatching = true
    val newState = reducer(state, action)
    state = newState
    observers foreach (_())
    isDispatching = false
    action
  }

  override def subscribe(listener: js.Function0[Unit]): Unit =
    observers += listener

}
