package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestsByOwnerServiceImpl implements TestsByOwnerService {
    private final DataBaseServiceImpl manager;
    @SuppressWarnings("all")
    private static TestsByOwnerServiceImpl service;

    public TestsByOwnerServiceImpl() {
        manager = DataBaseServiceImpl.getInstance();
    }

    public static synchronized TestsByOwnerServiceImpl getInstance() {
        if (service == null) return new TestsByOwnerServiceImpl();
        return service;
    }

    public double getCountOfPagesByOwner(HttpServletRequest req) {
        double count = manager.getTestManager().getAllFilterTestsForOwner(req.getParameter("test-name"), req.getParameter("test-subject"),
                req.getParameter("test-level"), req.getParameter("test-sort"), String.valueOf(req.getSession().getAttribute("id")), req.getParameter("test-order")) / 12;
        return Math.ceil(count);
    }

    public List<Test> getTestsByOwner(HttpServletRequest req) {
        return manager.getTestManager().getFilterResultForOwner(req.getParameter("test-name"), req.getParameter("test-subject"),
                req.getParameter("test-level"), req.getParameter("test-sort"), req.getParameter("test-order"), String.valueOf(req.getSession().getAttribute("id")), req.getParameter("page"));
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
        req.setAttribute("tests", getTestsByOwner(req));
        req.setAttribute("pages", getCountOfPagesByOwner(req));
        req.setAttribute("currentPage", req.getParameter("page"));
        req.setAttribute("address", getAddress(req).toString());
    }
}
