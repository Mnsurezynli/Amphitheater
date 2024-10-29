package org.example.Dto;

import org.example.Model.DayOfTime;
import org.example.Model.Time;

import javax.persistence.*;
import java.time.LocalDate;

public class ConferenceDto {

    private Long id;

    private DayOfTime dayOfTime;

    private Time time;

    private String organization;

    private String nameResponsible;

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfTime getDayOfTime() {
        return dayOfTime;
    }

    public void setDayOfTime(DayOfTime dayOfTime) {
        this.dayOfTime = dayOfTime;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getNameResponsible() {
        return nameResponsible;
    }

    public void setNameResponsible(String nameResponsible) {
        this.nameResponsible = nameResponsible;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
