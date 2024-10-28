package org.example.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "conference")
@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "dayOfTime")
    @Enumerated(EnumType.STRING)
    private DayOfTime dayOfTime;
    @Column(name = "time")
    @Enumerated(EnumType.STRING)
    private Time time;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "organization")
    private String organization;
    @Column(name = "nameResponsible")
    private String nameResponsible;

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
