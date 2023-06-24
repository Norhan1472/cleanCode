package com.spring.gucms.service;

import com.spring.gucms.entity.Location;
import com.spring.gucms.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, Location location) {
        Optional<Location> existingLocation = locationRepository.findById(id);

        if (existingLocation.isPresent()) {
            Location updatedLocation = existingLocation.get();
            updatedLocation.setFileName(location.getFileName());
            updatedLocation.setFileType(location.getFileType());
            updatedLocation.setStartDate(location.getStartDate());
            updatedLocation.setEndDate(location.getEndDate());
            updatedLocation.setFolder_id(location.getFolder_id());
            updatedLocation.setCreated_date(location.getCreated_date());
            updatedLocation.setCreated_user_id(location.getCreated_user_id());
            updatedLocation.setLast_updated_date(location.getLast_updated_date());
            updatedLocation.setLast_updated_user_id(location.getLast_updated_user_id());

            return locationRepository.save(updatedLocation);
        } else {
            return null;
        }
    }

    public boolean deleteLocationById(Long id) {
        Optional<Location> existingLocation = locationRepository.findById(id);

        if (existingLocation.isPresent()) {
            locationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
