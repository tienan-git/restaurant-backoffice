package jp.co.sparkworks.restaurant.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sparkworks.restaurant.backoffice.controller.param.SynchronizationReq;
import jp.co.sparkworks.restaurant.backoffice.service.WebAPIService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WebApiController {

    // {
    // "deviceId": "4b7f6caf6af3b9c4f546f60ba0472a3076e3dc24",
    // "data":[
    // {
    // "yutaponType":"red",
    // "spotName":"青巒荘",
    // "dateTime":"2018-09-08 14:23:50",
    // "status":"0"
    // },
    // {
    // "yutaponType": "green",
    // "spotName":"箱根",
    // "dateTime":"2018-09-08 23:09:18",
    // "status":"1"
    // }
    // ]
    // }
    //
	

	@Autowired
	WebAPIService webAPIService;
	
    @PostMapping("/synchronization")
    public ResponseEntity<HttpStatus> synchronization(@RequestBody SynchronizationReq req) {
        log.error("req........:" + req);
        
        webAPIService.synchronization(req.getDeviceId(), req.getData());
        
        
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);

    }
}
