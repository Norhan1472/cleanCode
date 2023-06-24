package com.spring.gucms.controller;

import com.spring.gucms.entity.Location;
import com.spring.gucms.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;
    @PostMapping("location/createLocation")
    public ResponseEntity<Location> create(@RequestBody Location location) {
        Location createdAuditationInfo = locationService.createLocation(location);//createAuditationInfo(auditationInfo);
        return new ResponseEntity<>(createdAuditationInfo, HttpStatus.CREATED);
    }

    // Read operation
    @GetMapping("location/getById/{id}")
    public ResponseEntity<Location> getById(@PathVariable Long id) {
        Location location = locationService.getLocationById(id).get();
        return new ResponseEntity<>(location, HttpStatus.OK);
    }
    // Get all operation
    @GetMapping("location/getAll")
    public ResponseEntity<List<Location>> getAll() {
        List<Location> location = locationService.getAllLocation();
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    // Update operation
    @PutMapping("location/updateLocation/{id}")
    public ResponseEntity<Location> update(@PathVariable Long id, @RequestBody Location location) {
        Location updatedLocation = locationService.updateLocation(id,location);
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }

    // Delete operation
    @DeleteMapping("location/deleteLocation//{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        locationService.deleteLocationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
