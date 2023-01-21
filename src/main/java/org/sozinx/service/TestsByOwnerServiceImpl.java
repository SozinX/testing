package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TestsByOwnerServiceImpl implements TestsByOwnerService {
    private final DataBaseServiceImpl manager;
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
        req.setAttribute("tests", getTestsByOwner(req));
        req.setAttribute("pages", getCountOfPagesByOwner(req));
        req.setAttribute("currentPage", req.getParameter("page"));
        req.setAttribute("address", getAddress(req).toString());
    }
}
