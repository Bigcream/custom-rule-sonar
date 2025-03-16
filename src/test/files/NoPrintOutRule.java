

public class HelloController {
  public String testPrint() { // Noncompliant {{Don't use Order here because it's an @Entity}}
    System.out.println("test");
    return null;
  }
}