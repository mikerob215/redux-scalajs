import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

object Utils {
  def combineReducers: js.Function2[js.Object, js.Object, js.Object] = ???

  @JSExportTopLevel("compose")
  def compose(fns: js.Function1[js.Any, js.Any]*): js.Function1[js.Any, js.Any] =
    fns.reduce(_ compose _)(_: js.Any)

  @JSExportTopLevel("bindActionCreators")
  def bindActionCreators(actionCreators: js.Object, dispatch: js.Function1[js.Any, js.Any]): Unit = {
    js.Object.keys(actionCreators).foreach(key => {
      actionCreators
        .asInstanceOf[js.Dictionary[js.Function1[js.Any, js.Any]]]
        .update(key, (args: js.Any*) => dispatch(actionCreators(args)))
    })
  }

  def applyMiddleware: Store = ???
}
