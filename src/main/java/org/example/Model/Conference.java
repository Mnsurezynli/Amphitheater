package org.example.Model;

import javax.persistence.*;
@Table(name = "conference")
@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayOfTime dayOfTime;
    @Enumerated(EnumType.STRING)
    private Time time;
    @Column(name = "organization")
    private String organization;

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
}
