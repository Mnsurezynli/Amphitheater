package org.example.Dto;

import org.example.Model.Conference;
import org.example.Model.Status;
import org.example.Model.User;

public class ReserveDto {
    private Long id;
    private Status status;
    private Conference conference;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
