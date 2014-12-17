package br.com.fluentesoftware.tcglminer.extractor;

import br.com.fluentesoftware.tcglminer.domain.Route;
import br.com.fluentesoftware.tcglminer.domain.WorkTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class Mediator {
    private final static String ROUTE_PAGE = "http://site.tcgrandelondrina.com.br:8082/soap/BuscaHorarios";
    private final static String TERMINAL = "2";
    private final static String WEEK = "1";
    private final static String SATURDAY = "2";
    private final static String SUNDAY = "3";
    private final static String TYPE = "tipo";
    private final static String ROUTE = "idLinha";
    private final static String DAY = "idDia";
    private final static String FROM = "idLinhaO";
    private final static String TO = "idLinhaD";
    private final static String CONVENCIONAL = "c";
    private final static String PSIU = "p";
    private final static int TIMEOUT = 3000;

    private final Parser parse = new Parser();
    private Route route;
    private String origin;
    private String destination;

    public Mediator extractWorkTimes() {
        System.out.println(this.route.getCode() + " " + this.origin + " " + this.destination);
        Document doc = null;
        try {
            doc = Jsoup.connect(ROUTE_PAGE)
                    .data(TYPE, CONVENCIONAL)
                    .data(ROUTE, this.route.getCode())
                    .data(DAY, WEEK)
                    .data(FROM, this.origin)
                    .data(TO, this.destination)
                    .timeout(TIMEOUT)
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<WorkTime> workTimes;

        workTimes = parse.scheduleTableFromDocument(doc);

        for (WorkTime wt : workTimes) {
            System.out.println(wt.getDeparture().toString() + " " + wt.getArrive().toString());
        }
         return this;
    }

    public Mediator toRoute(Route route) {
        this.route = route;
        return this;
    }
    public Mediator starting(String origin) {
        this.origin = origin;
        return this;
    }

    public Mediator ending(String destination) {
        this.destination = destination;
        return this;
    }
}
