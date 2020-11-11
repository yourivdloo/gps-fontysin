package fontysin.project.controllers;

import fontysin.project.exceptions.InternalServerException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Integer.parseInt;

public class Util {
    private Util() {
        throw new IllegalStateException("Cannot instantiate Utility Class");
    }

    public static int getPcn() {
        ServletRequestAttributes attrib = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attrib != null;
        HttpServletRequest request = attrib.getRequest();

        String email = request.getHeader("x-ms-client-principal-name");
        try {
            return parseInt(email.split("@")[0]);
        }
        catch (Exception ex) {
            throw new InternalServerException("Unable to parse PCN");
        }
    }

    public static boolean emptyOrNull(String[] list) {
        for (String input : list)
            if (input == null || input.isEmpty()) {
                return true;
            }
        return false;
    }

    public static boolean emptyOrNull(String input) {
        return input == null || input.isEmpty();
    }
}
