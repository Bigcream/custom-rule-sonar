/*
 * Copyright (C) 2012-2025 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
package com.example.customsonarrule;

import com.example.customsonarrule.checks.*;
import org.sonar.plugins.java.api.JavaCheck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RulesList {

  private RulesList() {
  }

  public static List<Class<? extends JavaCheck>> getChecks() {
    List<Class<? extends JavaCheck>> checks = new ArrayList<>();
    checks.addAll(getJavaChecks());
    checks.addAll(getJavaTestChecks());
    return Collections.unmodifiableList(checks);
  }

  /**
   * These rules are going to target MAIN code only
   */
  public static List<Class<? extends JavaCheck>> getJavaChecks() {
    return List.of(SpringControllerRequestMappingEntityRule.class,
            AvoidAnnotationRule.class,
            AvoidBrandInMethodNamesRule.class,
            AvoidMethodDeclarationRule.class,
            AvoidSuperClassRule.class,
            AvoidTreeListRule.class,
            MyCustomSubscriptionRule.class,
            NoSystemOutPrintRule.class,
            SecurityAnnotationMandatoryRule.class);
  }

  /**
   * These rules are going to target TEST code only
   */
  public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
    return List.of(NoIfStatementInTestsRule.class);
  }
}
