package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Test;

import java.util.List;

public interface TestsService {
    double getCountOfPages(HttpServletRequest req);

    List<Test> getTests(HttpServletRequest req);

    void setUserAttributes(HttpServletRequest req);

}
