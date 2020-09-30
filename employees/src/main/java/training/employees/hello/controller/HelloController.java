package training.employees.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import training.employees.hello.service.HelloService;

@Controller
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping("/api/hello")
    @ResponseBody
    public String sayHello() {
        return helloService.sayHello();
    }
}
