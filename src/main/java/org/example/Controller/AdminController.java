package org.example.Controller;

import org.example.Dto.ConferenceDto;
import org.example.Model.Conference;
import org.example.Model.Reserve;
import org.example.Service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/reserve/{reserveId}/confirm-reject")
    public ResponseEntity<Reserve> ConfirmOrRejectUser(@PathVariable Long reserveId, @RequestParam String a) {
        iAdminService.ConfirmOrRejectUser(reserveId, a);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/viewAllConference")
    public ResponseEntity<List<Conference>> viewAllConference() {
        List<Conference> conferences = iAdminService.viewAllConference();
        return new ResponseEntity<>(conferences, HttpStatus.OK);
    }

    @GetMapping("/viewHistoryOfConference")
    public ResponseEntity<List<Reserve>> viewHistoryOfConference() {
        List<Reserve> reserves = iAdminService.viewHistoryOfConference();
        return new ResponseEntity<>(reserves, HttpStatus.OK);
    }

    @PostMapping("/conference/{reserveId}/confirm-reject")
    public ResponseEntity<Reserve> ConfirmOrRejectConference(@PathVariable Long reserveId, @RequestParam boolean confirm) {
        iAdminService.ConfirmOrRejectConference(reserveId, confirm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
