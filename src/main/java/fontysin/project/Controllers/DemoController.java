package fontysin.project.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping(path = "/sso")
    public @ResponseBody ResponseEntity<String> getSSO() {
        int pcn = Util.GetPcn();
        return new ResponseEntity<String>("Your PCN is: " + pcn, HttpStatus.OK);
    }
}