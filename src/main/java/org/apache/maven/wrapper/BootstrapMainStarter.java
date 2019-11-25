/*
 * Copyright 2007-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.maven.wrapper;

import java.io.File;

/**
 * @author Hans Dockter
 */
public class BootstrapMainStarter {
  public void start(String[] args, File mavenHome) throws Exception {
    final String osName = System.getProperty("os.name");
    final boolean isWindows = osName != null && osName.startsWith("Windows");
    final String script = isWindows ? "mvn.cmd" : "mvn";

    final File mvnCmd = new File(mavenHome, "bin" + File.separator + script);

    final String[] command = new String[args.length + 1];
    command[0] = mvnCmd.getAbsolutePath();
    System.arraycopy(args, 0, command, 1, args.length);

    final Process process =
        new ProcessBuilder(command)
            .inheritIO()
            .start();
    System.exit(process.waitFor());
  }
}
