package ro.andreianghel.uiinputservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.andreianghel.uiinputservice.MessageProducerService;

@RestController
public class UIController {

    private MessageProducerService messageProducerService;

    private int counter;

    @Autowired
    public UIController(MessageProducerService messageProducerService) {
        this.messageProducerService = messageProducerService;
        this.counter = 0;
    }


    @GetMapping("demoAdd")
    String demoAdd() {

        // FIXME push a message to a kafka consumer

        messageProducerService.sendAddMessage("demo message: "+(counter++));




        return "hi";
    }


    @PostMapping("entry")
    public static void addNewEntry(@RequestBody String entryString) {
        // FIXME put the string on a message queue as a create message
    }

    @PutMapping("entry/{id}")
    public void updateEntry(@PathVariable("id") Integer id, @RequestBody String entryString) {
        // FIXME put a message with the id and the new content of the entry
    }

    @DeleteMapping("entry/{id}")
    public void deleteEntry(@PathVariable("id") Integer id) {
        // FIXME implement the delete request
        // put a request message on the queue
    }

}
