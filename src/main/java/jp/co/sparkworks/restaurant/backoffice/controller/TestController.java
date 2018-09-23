package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sparkworks.restaurant.backoffice.controller.param.HelloWorld;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // Get,Put,Post,Delete
    @GetMapping("/test")
    public HelloWorld test1() {
        // log.debug("test1 start ... ");
        return new HelloWorld(counter.incrementAndGet(), String.format(template, "Get"));
    }

    @PutMapping("/test")
    public HelloWorld test2() {
        // log.debug("test2 start ... ");
        return new HelloWorld(counter.incrementAndGet(), String.format(template, "Put"));
    }

    @PostMapping("/test")
    public HelloWorld test3() {
        // log.debug("test3 start ... ");
        return new HelloWorld(counter.incrementAndGet(), String.format(template, "Post"));
    }

    @DeleteMapping("/test")
    public HelloWorld test4() {
        // log.debug("test4 start ... ");
        return new HelloWorld(counter.incrementAndGet(), String.format(template, "Delete"));
    }

    // RequestParam
    @GetMapping("/param")
    public HelloWorld test5(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.debug("test5 start ... "+name);
        return new HelloWorld(counter.incrementAndGet(), String.format(template, "@RequestParam:" + name));
    }

    // PathVariablevvc
    @GetMapping("/param/{name}/{password}")
    public HelloWorld test6(@PathVariable String name, @PathVariable String password) {
        // log.debug("test99 start ... ");
        return new HelloWorld(counter.incrementAndGet(), String.format(template, "@PathVariable:" + name + password));
    }

    @PostMapping("/post")
    public HelloWorld test7(@RequestBody HelloWorld helloWorld) {
        // log.debug("test7 start ... ");
        return new HelloWorld(counter.incrementAndGet(), String.format(template, "@RequestBody:" + helloWorld));
    }

}
