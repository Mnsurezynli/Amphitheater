package org.example.Service.Impl;

import org.example.Dto.ConferenceDto;
import org.example.Exception.ResourceAlreadyExsits;
import org.example.Exception.ResourceNotFound;
import org.example.Model.Conference;
import org.example.Model.Reserve;
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
        Optional<Conference> conference = conferenceRepository.findById(conferenceDto.getId());
        if (conference.isPresent()) {
            throw new ResourceAlreadyExsits("this conference already exsits");
        } else {
            Conference conference1 = ConvertToEntity(conferenceDto);
            conference1 = conferenceRepository.saveAndFlush(conference1);
            ConferenceDto conferenceDto1 = ConvertToDto(conference1);
            return conferenceDto1;
        }
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

    public Reserve ConfirmOrRejectUser(Long reserveId, String a) {
        Reserve reserve = reserveRepository.findById(reserveId).get();
        if (a.equals("CONFIRM")) {
            reserve.setStatus(Status.CONFIRM);
        } else if (a.equals("REJECT")) {
            reserve.setStatus(Status.REJECT);
        } else {
            throw new IllegalArgumentException("a is invalid");
        }
        return reserveRepository.saveAndFlush(reserve);
    }


    public List<Conference> viewAllConference() {
        List<Conference> conferences = conferenceRepository.findAll();
        return conferences;
    }

    public List<Reserve> viewHistoryOfConference() {
        List<Reserve> reserves = reserveRepository.findAll();
        reserves.stream().filter(reserve -> reserve.getStatus().equals("CONFIRM")).collect(Collectors.toList());
        return reserves;
    }

    public Reserve ConfirmOrRejectConference(Long reserveId, boolean confirm) {
        Reserve reserves = reserveRepository.findById(reserveId)
                .orElseThrow(() -> new ResourceNotFound("this conference is not reserved"));
        if (confirm) {
            reserves.setStatus(Status.valueOf("CONFIRM"));
        } else {
            reserves.setStatus(Status.valueOf("REJECT"));
        }
        return reserveRepository.saveAndFlush(reserves);
    }

    public ConferenceDto ConvertToDto(Conference conference) {
        if (conference == null) {
            return null;
        }
        ConferenceDto conferenceDto = new ConferenceDto();
        conferenceDto.setId(conference.getId());
        conferenceDto.setTime(conference.getTime());
        conferenceDto.setDayOfTime(conference.getDayOfTime());
        conferenceDto.setOrganization(conference.getOrganization());
        conferenceDto.setNameResponsible(conference.getNameResponsible());
        return conferenceDto;
    }

    public Conference ConvertToEntity(ConferenceDto conferenceDto) {
        if (conferenceDto == null) {
            return null;
        }
        Conference conference = new Conference();
        conference.setId(conferenceDto.getId());
        conference.setTime(conferenceDto.getTime());
        conference.setOrganization(conferenceDto.getOrganization());
        conference.setDayOfTime(conferenceDto.getDayOfTime());
        conference.setNameResponsible(conferenceDto.getNameResponsible());
        conference.setDate(conferenceDto.getDate());
        return conference;
    }
}
