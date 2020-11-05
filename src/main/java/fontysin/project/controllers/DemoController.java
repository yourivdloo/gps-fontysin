package fontysin.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping(path = "/sso")
    public @ResponseBody ResponseEntity<String> getSSO() {
        int pcn = Util.GetPcn();
        return new ResponseEntity<String>("Your PCN is: " + pcn, HttpStatus.OK);
    }
}