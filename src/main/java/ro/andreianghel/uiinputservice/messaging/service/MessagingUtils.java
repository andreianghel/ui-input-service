package ro.andreianghel.uiinputservice.messaging.service;

import kafka.zk.AdminZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static kafka.admin.RackAwareMode.Disabled$.MODULE$;

@Component
public class MessagingUtils {

    @Autowired
    private static AdminZkClient adminZkClient;






    public static void createTopic(String topicName, int partitionsNumber, int replicationFactor) {

        Properties topicConfig = new Properties(); // you can pass topic configurations while creating topic

        adminZkClient.createTopic(topicName, partitionsNumber, replicationFactor, topicConfig, MODULE$);
    }


//    public static void checkTopicExists(String topicName) {
//        adminZkClient.topic
//    }
}
