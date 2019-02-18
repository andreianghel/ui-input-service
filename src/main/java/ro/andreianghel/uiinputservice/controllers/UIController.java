package ro.andreianghel.uiinputservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIController {

    @PostMapping("\newEntry")
    public static void addNewEntry(@RequestBody String entryString) {
        //FIXME put the string on a message queue
    }

}
