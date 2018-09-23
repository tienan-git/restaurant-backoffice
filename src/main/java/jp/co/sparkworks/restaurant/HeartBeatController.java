package jp.co.sparkworks.restaurant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HeartBeatController {

    @RequestMapping("/heartBeat")
    public String heartBeat() {
        return "OK";
    }

}
