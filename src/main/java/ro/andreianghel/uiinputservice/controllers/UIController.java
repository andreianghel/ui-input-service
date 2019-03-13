package ro.andreianghel.uiinputservice.controllers;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.andreianghel.uiinputservice.messaging.service.MessageSenderService;

import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController("/input")
public class UIController {

    private MessageSenderService messageSenderService;

    private int counter;

    Consumer<String, String> consumer;

    @Autowired
    public UIController(MessageSenderService messageSenderService) {
        this.messageSenderService = messageSenderService;
        this.counter = 0;
        this.consumer = null;
    }

    /**
     *
     * @return
     */
    @GetMapping("/demoAdd")
    String demoAdd() {

        // FIXME push a message to a kafka consumer

        messageSenderService.sendAddMessage("demo message: " + (counter++));


        return "hi";
    }

    /**
     *
     * @return
     */
    @GetMapping("/demoGetAll")
    List<String> demoGetAll() {

        List<String> retVal = new ArrayList<>();


        // FIXME get the messages from kafka add topic, just set the offste to 0

        // this should be recreated when a new user is detected
        if (consumer == null) {
            consumer = createConsumer(generateRandomString(20));
        } else {

            // this means that the consumer has the offset and any new poll will return only new values;
            consumer.resume(Stream.of(new TopicPartition(MessageSenderService.TOPIC_ADD, 0)).collect(Collectors.toList()));
        }


        //  while (true) {
        ConsumerRecords<String, String> records = consumer.poll(100);


        for (ConsumerRecord<String, String> record : records) {
            //Consume record
            // this should normally push to an web socket or something
            
            retVal.add(record.value());
        }


        consumer.pause(Stream.of(new TopicPartition(MessageSenderService.TOPIC_ADD, 0)).collect(Collectors.toList()));

        return retVal;
    }

    @GetMapping("getAll")
    public void getAllEntries() {
        messageSenderService.sendGetAllMessage();
    }

    /**
     *
     * @param entryString
     */
    @PostMapping
    public static void addNewEntry(@RequestBody String entryString) {
        // FIXME put the string on a message queue as a create message
    }

    /***
     *
     * @param id
     * @param entryString
     */
    @PutMapping("{id}")
    public void updateEntry(@PathVariable("id") Integer id, @RequestBody String entryString) {
        // FIXME put a message with the id and the new content of the entry
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteEntry(@PathVariable("id") Integer id) {
        // FIXME implement the delete request
        // put a request message on the queue
    }

    /**
     *
     * @param groupId
     * @return
     */
    private static Consumer<String, String> createConsumer(String groupId) {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // Create the consumer using props.
        final Consumer<String, String> consumer =
                new KafkaConsumer<>(props);
        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(MessageSenderService.TOPIC_ADD));


        System.out.println("#subscribed");
        return consumer;
    }


    /**
     *
     * @param length
     * @return
     */
    public String generateRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

}
