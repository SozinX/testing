package org.sozinx.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

public class CookiesServiceImpl implements CookiesService{
    @SuppressWarnings("unused")
    private static CookiesServiceImpl service;
    public static synchronized CookiesServiceImpl getInstance() {
        if (service == null) return new CookiesServiceImpl();
        return service;
    }

    public void filterCheckLanguage(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookie = req.getCookies();
        if (cookie == null) {
            resp.addCookie(new Cookie("language", "ua"));
        } else {
            for (int i = 0; i < cookie.length; i++) {
                if (Objects.equals(req.getCookies()[i].getName(), "language")) {
                    String language = req.getCookies()[i].getValue();
                    if (!Objects.equals(language, "ua") && !Objects.equals(language, "en")) {
                        resp.addCookie(new Cookie("language", "ua"));
                    }
                    req.setAttribute("language", req.getCookies()[i].getValue());
                    break;
                } else if (i == cookie.length - 1) {
                    resp.addCookie(new Cookie("language", "ua"));
                    req.setAttribute("language", "ua");
                }
            }
        }
    }
}
