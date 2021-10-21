package cn.com.demo.kafka.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 *
 */
@Data
@ToString
public class Message {
    private Long id;
    private String msg;
    private Date sendTime;
}
