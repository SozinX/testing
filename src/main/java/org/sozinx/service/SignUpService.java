package org.sozinx.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface SignUpService {
    String validationMessage(final HttpServletRequest req) throws ServletException, IOException;

    void insertData(final HttpServletRequest req);
}
