package chordtransposer.web.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class SampleController {

    @GetMapping("")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok().body("hi there!");
    }
}