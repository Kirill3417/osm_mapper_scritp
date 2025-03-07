package com.example.script.models;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.Objects;

public class DateInterval {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    Instant from = Instant.parse("2024-09-01T21:00:00Z");
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    Instant to = Instant.parse("2024-10-31T20:59:59Z");

    public DateInterval() {
    }

    public DateInterval(Instant from, Instant to) {
        this.from = from;
        this.to = to;
    }

    public Instant getFrom() {
        return from;
    }

    public void setFrom(Instant from) {
        this.from = from;
    }

    public Instant getTo() {
        return to;
    }

    public void setTo(Instant to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateInterval that = (DateInterval) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "DateInterval{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
