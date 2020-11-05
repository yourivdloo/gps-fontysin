package fontysin.project.controllers;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Integer.parseInt;

public class Util {
    static public int GetPcn() throws NumberFormatException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String email = request.getHeader("x-ms-client-principal-name");
        try {
            return parseInt(email.split("@")[0]);
        }
        catch (Exception ex) {
            return 0;
        }
    }

    static public boolean EmptyOrNull(String[] list) {
        for (String input : list)
            if (input == null || input.isEmpty()) {
                return true;
            }
        return false;
    }

    static public boolean EmptyOrNull(String input) {
        if (input == null || input.isEmpty()) {
            return true;
        }
        return false;
    }
}
