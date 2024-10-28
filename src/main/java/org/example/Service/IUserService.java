package org.example.Service;

import org.example.Dto.ReserveDto;
import org.example.Dto.UserDto;
import org.example.Model.Reserve;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserDto register(UserDto userDto);

    void Login(UserDto userDto);

    List<Reserve> viewEmptyTime();

    void cancelTheReserve(Long reserveId);

    Reserve reserveTheTime(ReserveDto reserveDto);

    Optional<Reserve> ViewTheStatusForReserve(Long reserveId);
}
