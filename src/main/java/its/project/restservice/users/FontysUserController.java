package its.project.restservice.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/users")
public class FontysUserController {
    @Autowired
    private FontysUserRepository fontysUserRepository;

    @PostMapping(path="/add")
    public @ResponseBody FontysUser addNewUser (@RequestParam String name, @RequestParam String email) {

        FontysUser n = new FontysUser();
        n.setName(name);
        n.setEmail(email);
        fontysUserRepository.save(n);
        return n;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<FontysUser> getAllUsers() {
        return fontysUserRepository.findAll();
    }
}