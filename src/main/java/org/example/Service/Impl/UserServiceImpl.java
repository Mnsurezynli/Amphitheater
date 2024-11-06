package org.example.Service.Impl;

import org.example.Dto.ReserveDto;
import org.example.Dto.UserDto;
import org.example.Exception.ResourceNotFound;
import org.example.Model.*;
import org.example.Repository.ConferenceRepository;
import org.example.Repository.ReserveRepository;
import org.example.Repository.UserRepository;
import org.example.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final ConferenceRepository conferenceRepository;
    private final ReserveRepository reserveRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ConferenceRepository conferenceRepository, ReserveRepository reserveRepository) {
        this.userRepository = userRepository;
        this.conferenceRepository = conferenceRepository;
        this.reserveRepository = reserveRepository;
    }

    @Transactional
    public UserDto register(UserDto userDto) {
        Optional<User> user = userRepository.findByUserName(userDto.getUserName());
        if (user.isPresent()) {
            throw new IllegalArgumentException("this user already exists");
        } else {
            User user1 = ConvertToEntity(userDto);
            user1.setStatus(Status.valueOf("PENDING"));
            user1 = userRepository.saveAndFlush(user1);
            UserDto userDto1 = ConvertToDto(user1);
            return userDto1;
        }
    }

    public String Login(UserDto userDto) {
        Optional<User> user = userRepository.findByUserNameAndPassword(userDto.getUserName(), userDto.getPassword());
        if (!user.isPresent()) {
            throw new ResourceNotFound("this user not found");
        } else {
            return "Login was successfully";
        }

    }

    public List<Time> viewEmptyTime(Long conferenceId) {
        Optional<Conference> conference = conferenceRepository.findById(conferenceId);
        List<Time> emptyTime;
        if (!conference.isPresent()) {
            throw new ResourceNotFound("this conference not found");
        } else {
            emptyTime = new ArrayList<>(Arrays.asList(Time.MORNING, Time.AFTERNOON));
            for (Reserve reserve : conference.get().getReserveList()) {
                emptyTime.remove(reserve.getTime());
            }
        }
        return emptyTime;
    }


    @Transactional
    public void cancelTheReserve(Long id) {
        Optional<Reserve> reserve = reserveRepository.findById(id);
        if (!reserve.isPresent()) {
            throw new ResourceNotFound("this reserve not found");
        } else {
            reserveRepository.deleteById(id);
        }
    }

    @Transactional
    public Reserve reserveTheTime(ReserveDto reserveDto) {
        if (reserveDto == null || reserveDto.getConferenceId() == null) {
            throw new IllegalArgumentException("conference id not be null");
        }
        Conference conference = conferenceRepository.findById(reserveDto.getConferenceId())
                .orElseThrow(() -> new RuntimeException("Conference not found"));
        Reserve reserve = ConvertToEntityReserveDto(reserveDto);
        reserve.setConference(conference);
        return reserveRepository.saveAndFlush(reserve);
    }

    public String getConferenceReserveStatus(Long reserveId) {
        Reserve reserve = reserveRepository.findById(reserveId)
                .orElseThrow(() -> new RuntimeException("Reserve request not found"));
        return String.valueOf(reserve.getStatus());
    }


    public UserDto ConvertToDto(User user) {
        UserDto userDto = new UserDto();
        if (user != null) {
            userDto.setId(user.getId());
            userDto.setUserName(user.getUserName());
            userDto.setPassword(user.getPassword());
            userDto.setEmail(user.getEmail());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setRole(user.getRole());
        }
        return userDto;
    }

    public User ConvertToEntity(UserDto userDto) {
        User user = new User();
        if (userDto != null) {
            user.setId(userDto.getId());
            user.setUserName(userDto.getUserName());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setRole(userDto.getRole());
        }
        return user;
    }

    public ReserveDto ConvertToDtoReserve(Reserve reserve) {
        ReserveDto reserveDto = new ReserveDto();
        if (reserve != null) {
            reserveDto.setId(reserve.getId());
            reserveDto.setUserId(reserve.getUser() != null ? reserve.getUser().getId() : null);
            reserveDto.setStatus(reserve.getStatus());
            reserveDto.setConferenceId(reserve.getConference() != null ? reserve.getConference().getId() : null);
        }
        return reserveDto;
    }

    public Reserve ConvertToEntityReserveDto(ReserveDto reserveDto) {
        Reserve reserve = new Reserve();
        if (reserveDto != null) {
            reserve.setId(reserveDto.getId());

            if (reserveDto.getUserId() != null) {
                User user = new User();
                user.setId(reserveDto.getUserId());
                reserve.setUser(user);
            }
            reserve.setStatus(reserveDto.getStatus());
            reserve.setDate(reserveDto.getDate());
            reserve.setTime(reserveDto.getTime());
        }
        return reserve;
    }


}
