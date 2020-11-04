package com.clj.gmalllogger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.clj.common.GmallConstants;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
//import com.clj.common;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author chen
 * @topic
 * @create 2020-11-03
 */
//@Controller
@RestController
@Slf4j
public class LoggerController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("test1")
    // @ResponseBody
    public String test01() {
        System.out.println("11111");
        return "Success";
    }

    @RequestMapping("log")
    public String getLogger(@RequestParam("logString") String logString) {

        //1.添加时间戳
        JSONObject jsonObject = JSON.parseObject(logString);
        jsonObject.put("ts",System.currentTimeMillis());

        //2.将jsonObject转化为字符串
        String jsonString = jsonObject.toJSONString();
//        com.clj.common.GmallConstants
        //落盘file
        log.info(jsonString);

        //3.推送kafka
        if ("startup".equals(jsonObject.getString("type"))){
            kafkaTemplate.send(GmallConstants.KAFKA_TOPIC_STARTUP,jsonString);

        }else {
            kafkaTemplate.send(GmallConstants.KAFKA_TOPIC_EVENT,jsonString);
        }


        return "Success";
    }
}
