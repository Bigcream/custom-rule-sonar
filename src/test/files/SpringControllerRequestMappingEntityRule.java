/*
 * Copyright (C) 2012-2024 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.persistence.Entity;

@Controller
public class HelloController {

  @RequestMapping("/updateOrder")
  public String updateOrder(Order order, Client client) { // Noncompliant {{Don't use Order here because it's an @Entity}}
//                          ^^^^^
    return null;
  }

  public String updateOrder2(Order order) { // Compliant
    return null;
  }

  @Entity
  public class Order {
    String ordered;
  }

  public class Client {
    String cliendId;
  }
}

class HelloClass {

  @RequestMapping("/updateOrder")
  public String updateOrder(Order order) { // Compliant
    return null;
  }


  @Entity
  public class Order {
    String ordered;
  }
}
