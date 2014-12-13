package br.com.fluentesoftware.tcglminer.domain;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Element;

public class WorkTime {
    private LocalTime departure;
    private LocalTime arrive;

    private LocalTime parseStringToDateTime(String string) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        return formatter.parseLocalTime(string);
    }

    public WorkTime(Element departure, Element arrive) {
        this.departure = this.parseStringToDateTime(departure.text());
        this.arrive = this.parseStringToDateTime(arrive.text());
    }

    public LocalTime getDeparture() {
        return this.departure;
    }

    public LocalTime getArrive() {
        return this.arrive;
    }
}
