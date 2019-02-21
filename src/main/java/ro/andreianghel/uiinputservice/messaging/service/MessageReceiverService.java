package ro.andreianghel.uiinputservice.messaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author AnAnghel on 2/21/2019
 */
@Service
public class MessageReceiverService {



    public static final String TOPIC_ADD_RESPONSE = "response_add_entry";
    public static final String TOPIC_GET_ALL_RESPONSE = "response_get_all_entries";

    private KafkaTemplate<String, String> kafkaTemplate;




    @Autowired
    public MessageReceiverService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = TOPIC_ADD_RESPONSE)
    public void consumeAddMessage(String message) {
       // FIXME nothing for now, allow duplicates

        // FIXME in the future return a value if duplicate

    }

    @KafkaListener(topics = TOPIC_GET_ALL_RESPONSE, groupId = "group_id")
    public void consumeGetAllMessage(String message) {
        System.out.println(String.format("#### TOPIC_GET_ALL_RESPONSE -> Consumed message -> %s", message));

        // add this message to the list in gui;


    }

}
