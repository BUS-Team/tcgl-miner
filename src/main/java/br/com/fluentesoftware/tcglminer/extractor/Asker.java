package br.com.fluentesoftware.tcglminer.extractor;

import br.com.fluentesoftware.tcglminer.domain.BusType;
import br.com.fluentesoftware.tcglminer.domain.Route;
import br.com.fluentesoftware.tcglminer.domain.RouteSet;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Asker {
    private final String ROUTE_SET = "http://site.tcgrandelondrina.com.br:8082/Soap/BuscarLinhas";
    private final String TERMINAL = "2";

    public void work() {
        Mediator mediator = new Mediator();

        RouteSet routeSet = this.getRoutes("Linhas Convencionais");

        for (Route r : routeSet.getRoutes()) {
            mediator.toRoute(r).starting(r.getCode()).ending(TERMINAL).extractWorkTimes();
            mediator.toRoute(r).starting(TERMINAL).ending(r.getCode()).extractWorkTimes();
        }
    }

    private RouteSet getRoutes(String routeType) {
        RouteSet[] routes = new RouteSet[0];
        BusType busType = new BusType(routeType, 0.99416611245616130f);

        Client client = ClientBuilder.newBuilder().build();

        WebTarget routeSource = client.target(ROUTE_SET);

        try {
            Response response = routeSource.request().post(Entity.entity(busType, MediaType.APPLICATION_JSON_TYPE));
            String json = response.readEntity(String.class);
            routes = new ObjectMapper().readValue(json, RouteSet[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return routes[0];
    }
}
