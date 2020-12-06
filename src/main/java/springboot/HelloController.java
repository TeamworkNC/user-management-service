package springboot;

import org.springframework.web.bind.annotation.RequestMapping;

public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot! This is user-management-service";
    }
}
