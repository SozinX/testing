package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestsServiceImpl implements TestsService {
    private final DataBaseService manager;
    @SuppressWarnings("all")
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
        return manager.getTestManager().getFilterResult(req.getParameter("test-name"), req.getParameter("test-subject"),
                req.getParameter("test-level"), req.getParameter("test-sort"), req.getParameter("test-order"), req.getParameter("page"));
    }

    //Create an uri query for saving  parameters after press "next" and "prev" buttons on page
    private StringBuilder getAddress(HttpServletRequest req) {
        StringBuilder getQuery = new StringBuilder();
        Map<String, String> values = valuesAddIntoMap(req);
        if (!isMapHasNull(values)) {
            values.forEach((key, value) -> {
                getQuery.append(key);
                if (!Objects.equals(value, "0")) {
                    getQuery.append(value);
                }
            });
        }
        return getQuery;
    }

    //Predict an error if one or many values is null
    private boolean isMapHasNull(Map<String, String> values) {
        AtomicBoolean hasNull = new AtomicBoolean(false);
        values.forEach((key, value) -> {
            if (value == null) {
                hasNull.set(true);
            }
        });
        return hasNull.get();
    }

    //Save values into map for using it in method before
    private Map<String, String> valuesAddIntoMap(HttpServletRequest req) {
        Map<String, String> values = new LinkedHashMap<>();
        values.put("&test-name=", req.getParameter("test-name"));
        values.put("&test-subject=", req.getParameter("test-subject"));
        values.put("&test-level=", req.getParameter("test-level"));
        values.put("&test-sort=", req.getParameter("test-sort"));
        values.put("&test-order=", req.getParameter("test-order"));
        return values;
    }

    //Set request scope attributes
    public void setUserAttributes(HttpServletRequest req) {
        req.setAttribute("tests", getTests(req));
        req.setAttribute("pages", getCountOfPages(req));
        req.setAttribute("currentPage", req.getParameter("page"));
        req.setAttribute("address", getAddress(req).toString());
    }
}
