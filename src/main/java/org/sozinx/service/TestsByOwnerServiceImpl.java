package org.sozinx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sozinx.model.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestsByOwnerServiceImpl implements TestsByOwnerService {
    private final DataBaseService manager;
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
        Map<String, String> criteriaOfFilter = Collections.synchronizedMap(new HashMap<>());
        criteriaOfFilter.put("name", req.getParameter("test-name"));
        criteriaOfFilter.put("subject", req.getParameter("test-subject"));
        criteriaOfFilter.put("level", req.getParameter("test-level"));
        criteriaOfFilter.put("owner", String.valueOf(req.getSession().getAttribute("id")));
        criteriaOfFilter.entrySet().stream().filter(criteria -> criteria.getValue() == null).
                forEach(criteria -> criteria.setValue("")); //if value null set "" in this value
        double count = manager.getTestManager().getAllFilterTestsForOwner(criteriaOfFilter) / 12; //12 tests on one page
        return Math.ceil(count);
    }

    //Building map for filtering in DAO class. More easy way to manage this process
    private Map<String, String> getFilterParameters(HttpServletRequest req) {
        Map<String, String> criteriaOfFilter = Collections.synchronizedMap(new HashMap<>());
        String orderColumn = req.getParameter("test-sort");
        if (Objects.equals(orderColumn, "") || orderColumn == null) { //predict first load error
            orderColumn = "name";
        }
        criteriaOfFilter.put("name", req.getParameter("test-name"));
        criteriaOfFilter.put("subject", req.getParameter("test-subject"));
        criteriaOfFilter.put("level", req.getParameter("test-level"));
        criteriaOfFilter.put("orderColumn", orderColumn);
        criteriaOfFilter.put("order", req.getParameter("test-order"));
        criteriaOfFilter.put("page", req.getParameter("page"));
        criteriaOfFilter.put("owner", String.valueOf(req.getSession().getAttribute("id")));
        return criteriaOfFilter;
    }

    //Sending to DAO map and getting results
    public List<Test> getTestsByOwner(HttpServletRequest req) {
        Map<String, String> criteriaOfFilter = getFilterParameters(req);
        criteriaOfFilter.entrySet().stream().filter(criteria -> criteria.getValue() == null).
                forEach(criteria -> criteria.setValue("")); //if value null set "" in this value
        return manager.getTestManager().getFilterResultForOwner(criteriaOfFilter);
    }

    //Create an uri query for saving parameters after press "next" and "prev" buttons on page
    private StringBuilder getAddress(HttpServletRequest req) {
        StringBuilder getQuery = new StringBuilder();
        Map<String, String> values = valuesAddIntoMap(req);
        if (!isMapHasNull(values)) {
            values.forEach((key, value) -> {
                getQuery.append(key);
                if (!Objects.equals(value, "0")) { //predict default values in some lists
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
