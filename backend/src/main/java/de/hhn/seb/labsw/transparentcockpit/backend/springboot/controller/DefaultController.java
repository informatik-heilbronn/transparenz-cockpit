package de.hhn.seb.labsw.transparentcockpit.backend.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
public class DefaultController {


    @RequestMapping("")
    private ResponseEntity<String> handleError() {
        return ResponseEntity.ok(LocalDateTime.now().getYear() + " Transparenz Cockpit by Team 6 HHN");
    }
}
