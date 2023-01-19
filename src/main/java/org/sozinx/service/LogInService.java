package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.User;

public interface LogInService {
    String inputIsCorrect(final HttpServletRequest req);
    User getCheckingUser();
}
