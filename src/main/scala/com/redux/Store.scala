package com.redux
import scala.collection.mutable.ArrayBuffer
import scala.scalajs.js

trait Action extends js.Object {
  val `type`: String
}

class Store(reducer: js.Function2[State, Action, State],
            preLoadedState: State)
    extends js.Object
    with StateContainer {
  private var state = preLoadedState
  private var observers: ArrayBuffer[js.Function0[Unit]] = ArrayBuffer()
  private var isDispatching = false

  def this(reducer: js.Function2[State, Action, State]) {
    this(reducer, js.Dynamic.literal().asInstanceOf[State])
  }

  dispatch(js.Dynamic.literal(`type` = "INIT").asInstanceOf[Action])

  override def dispatch(action: Action): Action = {
    if (js.typeOf(action) != "object")
      throw js.JavaScriptException("Actions must be plain js objects")

    isDispatching = true
    val newState: State = reducer(state, action)
    state = newState
    observers foreach (_())
    isDispatching = false
    action
  }

  override def subscribe(listener: js.Function0[Unit]): Unit =
    observers += listener

  override def getState(): State = state

}

trait StoreLiteral extends js.Object {
  val dispatch: Action => Action
  val getState: () => State
  val subscribe: () => Unit
}

object Store {
  def apply(reducer: ReducerFunction, preLoadedState: State): StoreLiteral =
    storeAsLiteral(new Store(reducer, preLoadedState))

  def apply(reducer: ReducerFunction): StoreLiteral =
    storeAsLiteral(new Store(reducer))

  def storeAsLiteral(store: Store): StoreLiteral = {
    js.Dynamic
      .literal(
        dispatch = store.dispatch _,
        getState = store.getState _,
        subscribe = store.subscribe _
      )
      .asInstanceOf[StoreLiteral]
  }

}
