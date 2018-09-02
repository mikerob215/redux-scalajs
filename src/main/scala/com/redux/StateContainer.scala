package com.redux
import scala.scalajs.js

trait StateContainer extends js.Any {
  def dispatch(action: Action): js.Object
  def subscribe(listener: js.Function0[Unit]): Unit
  def getState(): State
}
