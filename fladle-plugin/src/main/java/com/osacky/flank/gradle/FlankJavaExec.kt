package com.osacky.flank.gradle

import org.gradle.api.file.ProjectLayout
import org.gradle.api.tasks.JavaExec
import org.gradle.util.GradleVersion
import org.gradle.work.DisableCachingByDefault
import javax.inject.Inject

@DisableCachingByDefault(because = "Flank executions are dependent on resources such as network connection and server and therefore cannot be cached.")
open class FlankJavaExec @Inject constructor(projectLayout: ProjectLayout) : JavaExec() {
  init {
    group = FladlePluginDelegate.TASK_GROUP
    if (GradleVersion.current() >= GradleVersion.version("6.4")) {
      mainClass.set("ftl.Main")
    } else {
      main = "ftl.Main"
    }
    workingDir(projectLayout.fladleDir)
  }

  fun setUpWorkingDir(configName: String) = workingDir(project.layout.buildDirectory.dir("fladle/$configName"))
}
