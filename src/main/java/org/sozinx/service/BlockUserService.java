package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface BlockUserService {
    String inputIsCorrect(final HttpServletRequest req);

    void blockUser(HttpServletRequest req);
}
