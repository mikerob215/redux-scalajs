import scala.scalajs.js
trait StateContainer {
  this: Store =>
  private var state = js.Dynamic.literal()

  def dispatch(action: js.Object): js.Object
  def subscribe(listener: js.Function0[Unit]): Unit
  def getState(): js.Object with js.Dynamic = state
}
