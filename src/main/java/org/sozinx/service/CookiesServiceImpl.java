package org.sozinx.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

public class CookiesServiceImpl implements CookiesService {
    @SuppressWarnings("unused")
    private static CookiesServiceImpl service;

    public static synchronized CookiesServiceImpl getInstance() {
        if (service == null) return new CookiesServiceImpl();
        return service;
    }

    //Method for manage language in cookies. Setting UA language by default in all cases where the language is not set.
    public void filterCheckLanguage(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) { //if cookies is not present
            resp.addCookie(new Cookie("language", "ua")); //set language to "ua". P.S. Current law in Ukraine.
        } else {
            for (int i = 0; i < cookies.length; i++) { // checking every cookie
                if (Objects.equals(cookies[i].getName(), "language")) { //if loop in cookie which has name "language"
                    String language = cookies[i].getValue();
                    if (!Objects.equals(language, "ua") && !Objects.equals(language, "en")) { //if there is something different for allowed languages
                        resp.addCookie(new Cookie("language", "ua"));
                        language = "ua";
                    }
                    req.setAttribute("language", language); //setting request attribute
                    break;
                } else if (i == cookies.length - 1) { //if cookies list has ended
                    resp.addCookie(new Cookie("language", "ua")); //setting language by default
                    req.setAttribute("language", "ua");
                }
            }
        }
    }

}
