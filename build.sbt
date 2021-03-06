enablePlugins(ScalaJSPlugin)

name := "Redux-Scala.js"
scalaVersion := "2.12.6" // or any other Scala version >= 2.10.2

// This is an application with a main method
scalaJSUseMainModuleInitializer := true
scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
scalacOptions += "-P:scalajs:sjsDefinedByDefault"