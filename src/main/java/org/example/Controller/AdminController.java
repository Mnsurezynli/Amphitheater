package org.example.Controller;

import org.example.Dto.ConferenceDto;
import org.example.Model.Conference;
import org.example.Model.Reserve;
import org.example.Model.User;
import org.example.Service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private IAdminService iAdminService;

    @Autowired
    public AdminController(IAdminService iAdminService) {
        this.iAdminService = iAdminService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        iAdminService.deleteUser(id);
        return new ResponseEntity<>("the user deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ConferenceDto> createConference(@RequestBody ConferenceDto conferenceDto) {
        ConferenceDto conferenceDto1 = iAdminService.createConference(conferenceDto);
        return new ResponseEntity<>(conferenceDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteConference/{id}")
    public ResponseEntity<String> deleteConference(@PathVariable Long id) {
        iAdminService.deleteConference(id);
        return new ResponseEntity<>("the conference deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/confirm")
    public ResponseEntity<User> confirmUser(@PathVariable Long userId) {
        iAdminService.confirmUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/reject")
    public ResponseEntity<User> rejectUser(@PathVariable Long userId) {
        iAdminService.confirmUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/viewAllConference")
    public ResponseEntity<List<Conference>> viewAllConference() {
        List<Conference> conferences = iAdminService.viewAllConference();
        return new ResponseEntity<>(conferences, HttpStatus.OK);
    }

    @GetMapping("/viewHistoryOfConference")
    public ResponseEntity<List<Conference>> viewHistoryOfConference() {
        List<Conference> conferences = iAdminService.viewHistoryOfConference();
        return new ResponseEntity<>(conferences, HttpStatus.OK);
    }

    @PostMapping("/conference/{conferenceId}/confirm")
    public ResponseEntity<Conference> confirmConference(@PathVariable Long conferenceId) {
        iAdminService.confirmConference(conferenceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/conference/{conferenceId}/reject")
    public ResponseEntity<Conference> rejectConference(@PathVariable Long conferenceId) {
        iAdminService.confirmConference(conferenceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
