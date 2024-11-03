package org.example.Service;

import org.example.Dto.ReserveDto;
import org.example.Dto.UserDto;
import org.example.Model.Reserve;

import java.util.List;

public interface IUserService {

    UserDto register(UserDto userDto);

    void Login(UserDto userDto);

    List<Reserve> viewEmptyTime();

    void cancelTheReserve(Long id);

    ReserveDto createReserve(ReserveDto reserveDto);

    Reserve reserveTheTime(ReserveDto reserveDto);

    ReserveDto ViewTheStatusForReserve(Long id);
}
