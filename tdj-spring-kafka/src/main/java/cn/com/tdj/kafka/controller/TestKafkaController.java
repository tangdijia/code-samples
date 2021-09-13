package cn.com.tdj.kafka.controller;

import cn.com.tdj.kafka.consumer.KafkaConsumer;
import cn.com.tdj.kafka.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@Slf4j
public class TestKafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/message/send")
    public Boolean sendKafakMsg(@RequestParam String message) {
        kafkaProducer.send("app_log", message);
        return true;
    }

    @GetMapping("/message/send/sync")
    public Boolean sendSyncKafakMsg(@RequestParam String message) {
        kafkaProducer.sendSync("app_log", message);
        return true;
    }
}
