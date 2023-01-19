package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Test;

import java.util.*;

public class TestsServiceImpl implements TestsService {
    private final DataBaseServiceImpl manager;
    private static TestsServiceImpl service;

    public TestsServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized TestsServiceImpl getInstance() {
        if (service == null) return new TestsServiceImpl();
        return service;
    }

    public double getCountOfPages(HttpServletRequest req) {
        double count = manager.getTestManager().getAllFilterTests(req.getParameter("test-name"), req.getParameter("test-subject"),
                req.getParameter("test-level"), req.getParameter("test-sort"), req.getParameter("test-order")) / 12;
        return Math.ceil(count);
    }

    public List<Test> getTests(HttpServletRequest req) {
        return manager.getTestManager().getFilerResult(req.getParameter("test-name"), req.getParameter("test-subject"),
                req.getParameter("test-level"), req.getParameter("test-sort"), req.getParameter("test-order"), req.getParameter("page"));
    }

    private StringBuilder getAddress(HttpServletRequest req) {
        String name = req.getParameter("test-name");
        String subject = req.getParameter("test-subject");
        String level = req.getParameter("test-level");
        String orderColumn = req.getParameter("test-sort");
        String order = req.getParameter("test-order");
        StringBuilder getQuery = new StringBuilder();
        if (name != null && subject != null && orderColumn != null && level != null && order != null) {
            Map<String, String> values = new LinkedHashMap<>();
            values.put("&test-name=", name);
            values.put("&test-subject=", subject);
            values.put("&test-level=", level);
            values.put("&test-sort=", orderColumn);
            values.put("&test-order=", order);
            values.forEach((key, value) -> {
                getQuery.append(key);
                if (!Objects.equals(value, "0")) {
                    getQuery.append(value);
                }
            });
        }
        return getQuery;
    }

    public void setUserAttributes(HttpServletRequest req) {
        req.setAttribute("tests", getTests(req));
        req.setAttribute("pages", getCountOfPages(req));
        req.setAttribute("currentPage", req.getParameter("page"));
        req.setAttribute("address", getAddress(req).toString());
    }
}
