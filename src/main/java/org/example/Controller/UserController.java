package org.example.Controller;

import org.example.Dto.ReserveDto;
import org.example.Dto.UserDto;
import org.example.Model.Reserve;
import org.example.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        UserDto userDto1 = iUserService.register(userDto);
        return new ResponseEntity<>("the user register successfully", HttpStatus.CREATED);
    }

    @PostMapping("/Login")
    public ResponseEntity<Void> Login(@RequestBody UserDto userDto) {
        iUserService.Login(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/viewEmptyTime")
    public ResponseEntity<List<Reserve>> viewEmptyTime() {
        iUserService.viewEmptyTime();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> cancelTheReserve(@PathVariable Long id) {
        iUserService.cancelTheReserve(id);
        return new ResponseEntity<>("the reserve deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Reserve> reserveTheTime(@RequestBody ReserveDto reserveDto) {
        Reserve reserve1 = iUserService.reserveTheTime(reserveDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/ViewTheStatus/{reserveId}")
    public ResponseEntity<ReserveDto> ViewTheStatusForReserve(@PathVariable Long id) {
        ReserveDto reserveDto = iUserService.ViewTheStatusForReserve(id);
        return new ResponseEntity<>(reserveDto, HttpStatus.OK);
    }

}
