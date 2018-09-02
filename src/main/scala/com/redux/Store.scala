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

object Store {
  def apply(reducer: js.Function2[State, Action, State],
            preLoadedState: State): StoreLiteral =
    storeAsLiteral(new Store(reducer, preLoadedState))

  def apply(reducer: js.Function2[State, Action, State]): StoreLiteral =
    storeAsLiteral(new Store(reducer))

  def storeAsLiteral(store: Store): StoreLiteral = {
    js.Dynamic.literal(
      dispatch = store.dispatch _,
      getState = store.getState _,
      subscribe = store.subscribe _,
    )

    new StoreLiteral {
      override val dispatch: Action => Action = (action: Action) =>
        store.dispatch(action)
      override def getState(): State = store.getState()
      override def subscribe(observer: () => Unit): Unit =
        (listener: js.Function0[Unit]) => store.subscribe(listener)
    }
  }

  trait StoreLiteral extends js.Object {
    val dispatch: Action => Action
    def getState(): State
    def subscribe(observer: () => Unit): Unit
  }
}
