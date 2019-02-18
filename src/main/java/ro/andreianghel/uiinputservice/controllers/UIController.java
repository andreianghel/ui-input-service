package ro.andreianghel.uiinputservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class UIController {

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
