package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Test;

import java.util.List;

public interface TestsByOwnerService {
    double getCountOfPagesByOwner(HttpServletRequest req);

    List<Test> getTestsByOwner(HttpServletRequest req);

    void setUserAttributes(HttpServletRequest req);

}
