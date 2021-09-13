package cn.com.tdj.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.ExecutionException;

/**
 *
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 异步提交
     */
    public void send(String topic, Object msg) {
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, msg);
        send.addCallback(new SuccessCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                log.info(stringObjectSendResult.toString());
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error(throwable.getMessage(), throwable);
            }
        });
        log.info("send msg: [{}] to topic: [{}]", topic, msg);
    }

    public void sendSync(String topic, Object msg) {
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, msg);
        try {
            SendResult<String, Object> stringObjectSendResult = send.get();
            log.info(stringObjectSendResult.toString());
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            log.error(e.getMessage(), e);
        }
        log.info("send msg: [{}] to topic: [{}]", topic, msg);
    }
}
