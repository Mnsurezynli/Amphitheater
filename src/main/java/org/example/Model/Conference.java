package org.example.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "conference")
@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
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
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
    private List<Reserve> reserveList;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Reserve> getReserveList() {
        return reserveList;
    }

    public void setReserveList(List<Reserve> reserveList) {
        this.reserveList = reserveList;
    }
}
