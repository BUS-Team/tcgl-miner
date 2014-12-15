package br.com.fluentesoftware.tcglminer.extractor;

import br.com.fluentesoftware.tcglminer.domain.WorkTime;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<WorkTime> scheduleTableFromDocument(Document doc) {
        List<WorkTime> workTimes = new ArrayList<>();

        Element t1 = doc.getElementById("idTabHoraria");
        workTimes.addAll(this.parseTable(t1));

        try {
            Element t2 = doc.getElementById("idTabHoraria2");
            workTimes.addAll(this.parseTable(t2));
        } catch (NullPointerException e) { /* There isn't table 2 */}

        return workTimes;
    }

    private List<WorkTime> parseTable(Element table) {
        List<WorkTime> workTimes = new ArrayList<>();

        Elements trs = table.getElementsByTag("tr");

        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");

            Element departure = tds.first().getElementsByTag("abbr").first();
            Element arrive = tds.get(2).getElementsByTag("abbr").first();

            try {
                WorkTime workTime = new WorkTime(departure, arrive);
                workTimes.add(workTime);
            } catch (NullPointerException e) { /* Cause html has bad quality */ }
        }
        return workTimes;
    }
}
