package org.sozinx.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface SettingsService {
    String validationMessage(final HttpServletRequest req) throws ServletException, IOException;
    void editData(final HttpServletRequest req);
}
