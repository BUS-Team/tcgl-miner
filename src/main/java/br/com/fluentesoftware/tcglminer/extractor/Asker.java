package br.com.fluentesoftware.tcglminer.extractor;

import br.com.fluentesoftware.tcglminer.domain.BusType;
import br.com.fluentesoftware.tcglminer.domain.WorkTime;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

public class Asker {

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

    public void work() {

        BusType busType = new BusType("Linhas Convencionais", 0.99416611245616130f);

        try {
            Client client = ClientBuilder.newBuilder().build();

            WebTarget target = client.target("http://site.tcgrandelondrina.com.br:8082/Soap/BuscarLinhas");
            Response response = target.request().post(Entity.entity(busType, "application/json"));
            System.out.println(response.readEntity(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }






        Document doc = null;
        try {
            doc = Jsoup.connect("http://site.tcgrandelondrina.com.br:8082/soap/BuscaHorarios")
                    .data(TYPE, CONVENCIONAL)
                    .data(ROUTE, "307")
                    .data(DAY, WEEK)
                    .data(FROM, "2")
                    .data(TO, "307")
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
    }
}
