/*
 * Copyright (C) 2012-2025 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
package org.sonar.samples.java.checks;

import com.example.customsonarrule.checks.NoSystemOutPrintRule;
import com.example.customsonarrule.checks.SpringControllerRequestMappingEntityRule;
import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;
import org.sonar.samples.java.utils.FilesUtils;

class NoPrintOutTest {

  @Test
  void check() {
    CheckVerifier.newVerifier()
      .onFile("src/test/files/NoPrintOutRule.java")
      .withCheck(new NoSystemOutPrintRule())
      .withClassPath(FilesUtils.getClassPath("target/test-jars"))
      .verifyIssues();
  }

}
