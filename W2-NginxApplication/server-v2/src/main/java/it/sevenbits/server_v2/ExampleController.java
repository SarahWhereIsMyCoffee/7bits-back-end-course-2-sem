package it.sevenbits.server_v2;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/server_V2")
public class ExampleController {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Example> example() {
        System.out.println("Example log");
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(new Example());
    }
}
