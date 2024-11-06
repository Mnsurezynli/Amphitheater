package org.example.Service.Impl;

import org.example.Dto.ConferenceDto;
import org.example.Exception.ResourceNotFound;
import org.example.Model.Conference;
import org.example.Model.Status;
import org.example.Model.User;
import org.example.Repository.ConferenceRepository;
import org.example.Repository.ReserveRepository;
import org.example.Repository.UserRepository;
import org.example.Service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements IAdminService {

    private final UserRepository userRepository;

    private final ConferenceRepository conferenceRepository;

    private final ReserveRepository reserveRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, ConferenceRepository conferenceRepository, ReserveRepository reserveRepository) {
        this.userRepository = userRepository;
        this.conferenceRepository = conferenceRepository;
        this.reserveRepository = reserveRepository;
    }

    @Transactional
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ResourceNotFound("this user not found");
        } else {
            userRepository.deleteById(id);
        }
    }

    @Transactional
    public ConferenceDto createConference(ConferenceDto conferenceDto) {
        Conference conference1 = ConvertToEntity(conferenceDto);
        conference1 = conferenceRepository.saveAndFlush(conference1);
        ConferenceDto conferenceDto1 = ConvertToDto(conference1);
        return conferenceDto1;

    }

    @Transactional
    public void deleteConference(Long id) {
        Optional<Conference> conference = conferenceRepository.findById(id);
        if (!conference.isPresent()) {
            throw new ResourceNotFound("this conference not found");
        } else {
            conferenceRepository.deleteById(id);
        }
    }

    @Transactional
    public Optional<User> confirmUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setStatus(Status.valueOf("CONFIRM"));
            return user;
        } else {
            throw new ResourceNotFound("this user not found");
        }
    }

    @Transactional
    public Optional<User> rejectUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setStatus(Status.valueOf("REJECT"));
            return user;
        } else {
            throw new ResourceNotFound("this user not found");
        }
    }

    public List<Conference> viewAllConference() {
        List<Conference> conferences = conferenceRepository.findAll();
        return conferences;
    }

    public List<Conference> viewHistoryOfConference() {
        List<Conference> conferences = conferenceRepository.findAll();
        conferences.stream().filter(conference -> conference.getStatus().equals("CONFIRM")).collect(Collectors.toList());
        return conferences;
    }

    @Transactional
    public Optional<Conference> confirmConference(Long conferenceId) {
        Optional<Conference> conference = conferenceRepository.findById(conferenceId);
        if (conference.isPresent()) {
            Conference conference1 = conference.get();
            conference1.setStatus(Status.valueOf("CONFIRM"));
            return conference;
        } else {
            throw new ResourceNotFound("this conference not found");
        }
    }

    @Transactional
    public Optional<Conference> rejectConference(Long conferenceId) {
        Optional<Conference> conference = conferenceRepository.findById(conferenceId);
        if(conference.isPresent()){
            Conference conference1 =conference.get();
            conference1.setStatus(Status.valueOf("REJECT"));
            return conference;
        }else {
            throw new ResourceNotFound("this conference not found");
        }
    }

    public ConferenceDto ConvertToDto(Conference conference) {
        if (conference.getId() == null) {
            throw new IllegalArgumentException("id not be null");
        }
        ConferenceDto conferenceDto = new ConferenceDto();
        conferenceDto.setId(conference.getId());
        conferenceDto.setTime(conference.getTime());
        conferenceDto.setStatus(conference.getStatus());
        conferenceDto.setDate(conference.getDate());
        conferenceDto.setTitle(conference.getTitle());
        conferenceDto.setDayOfTime(conference.getDayOfTime());
        conferenceDto.setOrganization(conference.getOrganization());
        conferenceDto.setNameResponsible(conference.getNameResponsible());
        return conferenceDto;
    }

    public Conference ConvertToEntity(ConferenceDto conferenceDto) {
        Conference conference = new Conference();
        conference.setTime(conferenceDto.getTime());
        conference.setOrganization(conferenceDto.getOrganization());
        conference.setDayOfTime(conferenceDto.getDayOfTime());
        conference.setNameResponsible(conferenceDto.getNameResponsible());
        conference.setDate(conferenceDto.getDate());
        conference.setId(conferenceDto.getId());
        conference.setStatus(conferenceDto.getStatus());
        conference.setTitle(conferenceDto.getTitle());
        return conference;
    }
}
