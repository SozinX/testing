package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface UnblockUserService {
    String inputIsCorrect(final HttpServletRequest req);

    void unblockUser(HttpServletRequest req);
}
