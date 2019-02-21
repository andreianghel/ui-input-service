package ro.andreianghel.uiinputservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.andreianghel.uiinputservice.messaging.service.MessageSenderService;

@RestController
public class UIController {

    private MessageSenderService messageSenderService;

    private int counter;

    @Autowired
    public UIController(MessageSenderService messageSenderService) {
        this.messageSenderService = messageSenderService;
        this.counter = 0;
    }


    @GetMapping("demoAdd")
    String demoAdd() {

        // FIXME push a message to a kafka consumer

        messageSenderService.sendAddMessage("demo message: " + (counter++));


        return "hi";
    }

    @GetMapping("demoGetAll")
    void demoGetAll() {
        messageSenderService.sendGetAllMessage();
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
