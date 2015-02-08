package br.com.fluentesoftware.tcglminer.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RouteSet {
    private List<String> codes;
    private List<String> values;

    @JsonProperty(value = "cod")
    private List<String> getCodes() {
        return this.codes;
    }

    @JsonProperty(value = "valor")
    private List<String> getValues() {
        return this.values;
    }

    public List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();
        List<String> codes = this.getCodes();
        List<String> names = this.getValues();

        for (int i = 0; i < codes.size(); i++) {
            Route route = new Route(codes.get(i), names.get(i));
            routes.add(route);
        }
        return routes;
    }
}
