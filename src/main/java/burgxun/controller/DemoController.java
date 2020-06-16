package burgxun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoController
 * @Auther burgxun
 * @Description:
 * @Date 2020/06/10 11:10
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/test")
    public String test() {
        return "Hello Spring boot";
    }

    @GetMapping("/test2")
    public String test2() {
        return "Hello Spring boot";
    }
}
