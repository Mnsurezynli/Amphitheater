package org.example.Service;

import org.example.Dto.ConferenceDto;
import org.example.Model.Conference;
import org.example.Model.User;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    void deleteUser(Long id);

    void deleteConference(Long id);

    Optional<User> confirmUser(Long userId);

    Optional<User> rejectUser(Long userId);

    ConferenceDto createConference(ConferenceDto conferenceDto);

    List<Conference> viewAllConference();

    List<Conference> viewHistoryOfConference();

    Optional<Conference> confirmConference(Long conferenceId);

    Optional<Conference> rejectConference(Long conferenceId);
}
