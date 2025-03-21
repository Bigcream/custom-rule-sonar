/*
 * Copyright (C) 2012-2024 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
package org.foo.bar;

import java.util.stream.Stream;
import org.junit.Test;

public class ATest {

  private boolean someCondition = false;

  @Test
  public void myTest() {
    if (someCondition) { // Noncompliant {{Remove this 'if' statement from this test.}}
//  ^^
      // verify something
    }
  }

  @Test
  public void myOtherTest() {
    // verify something

    Stream.of(new Object()).forEach(o -> { 
      if(o.toString() > 42) { // Compliant - not properly part of a test, it's within a lambda!
        // do something 
      }
    });

    Object o = new Object() {
      @Override
      public String toString() {
        if (someCondition) { // compliant - not properly part of a test, it's within an anonymous class!
          return "42";
        }
        return super.toString();
      }
    };
  }

  @MyAnnotation
  public void myMethod() {
    if (someCondition) { // Compliant - not a test method
      // do something
    }
  }

  public abstract class AbstractTest {
    @Test
    public abstract void myTest();
  }

  @interface MyAnnotation { }
}
