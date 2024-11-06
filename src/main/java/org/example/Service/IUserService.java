package org.example.Service;

import org.example.Dto.ReserveDto;
import org.example.Dto.UserDto;
import org.example.Model.Reserve;
import org.example.Model.Time;

import java.util.List;

public interface IUserService {

    UserDto register(UserDto userDto);

    String Login(UserDto userDto);

    List<Time> viewEmptyTime(Long conferenceId);

    void cancelTheReserve(Long id);

    Reserve reserveTheTime(ReserveDto reserveDto);

    String getConferenceReserveStatus(Long reserveId);
}
