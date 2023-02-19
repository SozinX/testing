package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;

public interface SettingsService {
    String validationMessage(final HttpServletRequest req);

    void editData(final HttpServletRequest req);
}
