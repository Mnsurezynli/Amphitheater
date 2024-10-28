package org.example.Service;

import org.example.Model.Conference;
import org.example.Model.Reserve;

import java.util.List;

public interface IAdminService {
    void deleteUser(Long id);

    void deleteConference(Long id);

    Reserve ConfirmOrRejectUser(Long reserveId, String a);

    List<Conference> viewAllConference();

    List<Reserve> viewHistoryOfConference();

    Reserve ConfirmOrRejectConference(Long reserveId, boolean confirmed);
}