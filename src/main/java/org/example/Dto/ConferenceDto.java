package org.example.Dto;

import org.example.Model.DayOfTime;
import org.example.Model.Status;
import org.example.Model.Time;

import javax.persistence.*;
import java.time.LocalDate;

public class ConferenceDto {
    private Long id;
    private String title;
    private DayOfTime dayOfTime;
    private Time time;
    private LocalDate date;
    private String organization;
    private String nameResponsible;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}