/*
 * Copyright (C) 2012-2024 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */


public class HelloController {
  public String testPrint() { // Noncompliant {{Don't use Order here because it's an @Entity}}
    System.out.println("test");
    return null;
  }
}