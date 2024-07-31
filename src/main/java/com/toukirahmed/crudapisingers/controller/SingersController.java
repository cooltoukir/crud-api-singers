package com.toukirahmed.crudapisingers.controller;

import com.toukirahmed.crudapisingers.entity.Singers;
import com.toukirahmed.crudapisingers.service.SingersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/singers")
public class SingersController {

    @Autowired
    private SingersService singersService;

    // Create
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody Singers singers) {
        ResponseEntity<String> responseEntity = null;
        try {
            Integer integer = singersService.saveSingers(singers);
            responseEntity = new ResponseEntity<String>("Singer '" + integer + "' created", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return  responseEntity;
    }

    // update
    @PutMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody Singers singers) {
        ResponseEntity<String> responseEntity = null;
        boolean available = singersService.isAvailable(singers.getSingersPosition());
        if (available) {
            singersService.update(singers);
            responseEntity = new ResponseEntity<String>("Updated successfully", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<String>("Record '" + singers.getSingersPosition() + "' not found", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    // Delete
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;
        boolean available = singersService.isAvailable(id);
        if (available) {
            singersService.delete(id);
            responseEntity = new ResponseEntity<String>("Deleted " + id + " successfully", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<String>(id + " Not Exist", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    // Retrieve/Fetch
    @GetMapping(value = "/getSingleSinger/{id}")
    public ResponseEntity getSingleSingerById(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;
        if (singersService.isAvailable(id)) {
            Singers oneSinger = singersService.getOneSinger(id);
            responseEntity = new ResponseEntity<Singers>(oneSinger, HttpStatus.OK);
        } else {
            return new ResponseEntity("Record Not Found", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    // Fetch operations
    @GetMapping(value = "/getAllSingers")
    public ResponseEntity getAllSingers() {
        ResponseEntity responseEntity = null;
        List<Singers> allSingers = singersService.getAllSingers();
        if (allSingers == null || allSingers.isEmpty()) {
            String message = "No Data Found";
            responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Singers>>(allSingers, HttpStatus.OK);
        }
        return responseEntity;
    }
}
