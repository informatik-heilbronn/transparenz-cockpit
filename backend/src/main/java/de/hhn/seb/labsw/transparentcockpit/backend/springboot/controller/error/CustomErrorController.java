package de.hhn.seb.labsw.transparentcockpit.backend.springboot.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@RestController
public class CustomErrorController {


    @RequestMapping("/error")
    private ResponseEntity<Void> handleError(
            HttpServletRequest request
    ) {
        HttpStatus status = (HttpStatus) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            return new ResponseEntity<>(HttpStatus.valueOf(statusCode));
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
