package tech.fiszki.logic;

import java.time.LocalDateTime;

public class Repetition {
    private int id;
    private LocalDateTime date;
    private double successRate;

    public Repetition(LocalDateTime date, double successRate) {
        this.date = date;
        this.successRate = successRate;
    }
}
