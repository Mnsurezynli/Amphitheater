package org.example.Service.Impl;

import org.example.Dto.ReserveDto;
import org.example.Dto.UserDto;
import org.example.Exception.ResourceNotFound;
import org.example.Model.Reserve;
import org.example.Model.Status;
import org.example.Model.User;
import org.example.Repository.ConferenceRepository;
import org.example.Repository.ReserveRepository;
import org.example.Repository.UserRepository;
import org.example.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void Login(UserDto userDto) {
        Optional<User> user = userRepository.findByUserNameAndPassword(userDto.getUserName(), userDto.getPassword());
        if (!user.isPresent()) {
            throw new ResourceNotFound("this user not found");
        } else {
            System.out.println("Login was successfully");
        }
    }

    public List<Reserve> viewEmptyTime() {
        List<Reserve> reserves = reserveRepository.findAll();
        List<Reserve> reserveList = reserves.stream().filter(reserve -> reserve.getStatus().equals("CONFIRM")).collect(Collectors.toList());

        List<Reserve> timeSlot = reserveRepository.findAll();
        timeSlot.removeAll(reserveList);
        return timeSlot;
    }

    @Transactional
    public ReserveDto createReserve(ReserveDto reserveDto) {
        Reserve reserve = ConvertToEntityReserveDto(reserveDto);
        reserve = reserveRepository.saveAndFlush(reserve);
        ReserveDto reserveDto1 = ConvertToDtoReserve(reserve);
        return reserveDto1;

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
        Reserve reserve = ConvertToEntityReserveDto(reserveDto);
        reserve.setStatus(Status.PENDING);
        return reserveRepository.saveAndFlush(reserve);
    }

    @Override
    public ReserveDto ViewTheStatusForReserve(Long id) {
        Reserve reserve = reserveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("this reserveId not found"));
        return ConvertToDtoReserve(reserve);
    }


    public UserDto ConvertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public User ConvertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRole(userDto.getRole());
        return user;
    }

    public ReserveDto ConvertToDtoReserve(Reserve reserve) {
        ReserveDto reserveDto = new ReserveDto();
        reserveDto.setId(reserve.getId());
        reserveDto.setUserId(reserve.getId());
        reserveDto.setStatus(reserve.getStatus());
       reserveDto.setConferenceId(reserve.getId());
        return reserveDto;
    }

    public Reserve ConvertToEntityReserveDto(ReserveDto reserveDto) {
        Reserve reserve = new Reserve();
        reserve.setId(reserveDto.getId());
        reserve.setUser(reserveDto.getUser());
        reserve.setStatus(reserveDto.getStatus());
        reserve.setConference(reserveDto.getConference());
        return reserve;
    }
}
