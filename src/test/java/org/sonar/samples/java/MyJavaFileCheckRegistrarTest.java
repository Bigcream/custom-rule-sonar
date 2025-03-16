/*
 * SonarQube Java
 * Copyright (C) 2012-2024 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.java;

import com.example.customsonarrule.MyJavaFileCheckRegistrar;
import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;
import org.sonar.java.checks.verifier.TestCheckRegistrarContext;

import static org.assertj.core.api.Assertions.assertThat;

class MyJavaFileCheckRegistrarTest {

  @Test
  void checkRegisteredRulesKeysAndClasses() {
    TestCheckRegistrarContext context = new TestCheckRegistrarContext();

    MyJavaFileCheckRegistrar registrar = new MyJavaFileCheckRegistrar();
    registrar.register(context);

    assertThat(context.mainRuleKeys).extracting(RuleKey::toString).containsExactly(
      "mycompany-java:SpringControllerRequestMappingEntity",
      "mycompany-java:AvoidAnnotation",
      "mycompany-java:AvoidBrandInMethodNames",
      "mycompany-java:AvoidMethodDeclaration",
      "mycompany-java:AvoidSuperClass",
      "mycompany-java:AvoidTreeList",
      "mycompany-java:AvoidMethodWithSameTypeInArgument",
      "mycompany-java:SecurityAnnotationMandatory");

    assertThat(context.mainCheckClasses).extracting(Class::getSimpleName).containsExactly(
      "SpringControllerRequestMappingEntityRule",
      "AvoidAnnotationRule",
      "AvoidBrandInMethodNamesRule",
      "AvoidMethodDeclarationRule",
      "AvoidSuperClassRule",
      "AvoidTreeListRule",
      "MyCustomSubscriptionRule",
      "SecurityAnnotationMandatoryRule");

    assertThat(context.testRuleKeys).extracting(RuleKey::toString).containsExactly(
      "mycompany-java:NoIfStatementInTests");

    assertThat(context.testCheckClasses).extracting(Class::getSimpleName).containsExactly(
      "NoIfStatementInTestsRule");
  }

}
