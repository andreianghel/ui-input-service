package ro.andreianghel.uiinputservice.messaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

    public static final String TOPIC_ADD = "add_entry";
    public static final String TOPIC_GET_ALL = "get_all_entries";

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MessageSenderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAddMessage(String message) {
        sendMessage(TOPIC_ADD, message);
    }

    public void sendGetAllMessage() {
        sendMessage(TOPIC_GET_ALL, "");
    }

    public void sendMessage(String topic, String message) {
        // System.out.println(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(topic, message);
    }

}
