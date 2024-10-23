package org.example.Service.Impl;

import org.example.Dto.ConferenceDto;
import org.example.Model.Conference;
import org.example.Repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImpl {

    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public ConferenceDto ConvertToDto(Conference conference) {
        ConferenceDto conferenceDto = new ConferenceDto();
        conferenceDto.setId(conference.getId());
        conferenceDto.setOrganization(conference.getOrganization());
        conferenceDto.setTime(conference.getTime());
        conferenceDto.setDayOfTime(conference.getDayOfTime());
        return conferenceDto;
    }


    public Conference ConvertToEntity(ConferenceDto conferenceDto) {
        Conference conference = new Conference();
        conference.setId(conferenceDto.getId());
        conference.setOrganization(conferenceDto.getOrganization());
        conference.setTime(conferenceDto.getTime());
        conference.setDayOfTime(conferenceDto.getDayOfTime());
        return conference;
    }
}
