package com.redux
import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.scalajs.js.annotation.JSExportTopLevel

@js.native
trait ReducerFunction extends js.Function2[State, Action, State]

object Utils {
  def combineReducers(
    reducerMap: js.Dictionary[ReducerFunction]
  ): ReducerFunction = ???

  @JSExportTopLevel("compose")
  def compose(
    fns: js.Function1[js.Any, js.Any]*
  ): js.Function1[js.Any, js.Any] =
    fns.reduce(_ compose _)(_: js.Any)

  @JSExportTopLevel("bindActionCreators")
  def bindActionCreators(
    actionCreators: js.Object,
    dispatch: js.Function1[js.Any, js.Any]
  ): js.Dictionary[js.Function1[_, _]] = {
    val actionCreatorDictionary: Dictionary[js.Function1[_, _]] =
      actionCreators.asInstanceOf[js.Dictionary[js.Function1[_, _]]]
    val ret =
      js.Dynamic.literal().asInstanceOf[js.Dictionary[js.Function1[_, _]]]
    for ((prop, value) <- actionCreatorDictionary) {
      ret.update(prop, (args: js.Any) => dispatch(value.call(null, args)))
    }
    ret
  }

  def applyMiddleware: Store = ???
}
