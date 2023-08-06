lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """todosmongo""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.10",
    libraryDependencies ++= Seq(
      guice,
      "com.google.inject" % "guice" % "5.1.0",
      "com.google.inject.extensions" % "guice-assistedinject" % "5.1.0",
      "com.h2database" % "h2" % "1.4.200",
      "org.mongodb.scala" %% "mongo-scala-driver" % "4.10.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
