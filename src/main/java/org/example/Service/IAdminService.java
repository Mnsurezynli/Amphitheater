package org.example.Service;

import org.example.Dto.ConferenceDto;
import org.example.Model.Conference;
import org.example.Model.Reserve;

import java.util.List;

public interface IAdminService {
    void deleteUser(Long id);

    void deleteConference(Long id);

    void confirmUser(Long userId);

    void rejectUser(Long userId);

    ConferenceDto createConference(ConferenceDto conferenceDto);

    List<Conference> viewAllConference();

    List<Conference> viewHistoryOfConference();

    void confirmConference(Long conferenceId);

    void rejectConference(Long conferenceId);
}
