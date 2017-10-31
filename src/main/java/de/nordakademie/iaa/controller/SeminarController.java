package de.nordakademie.iaa.controller;


import de.nordakademie.iaa.model.Seminar;
import de.nordakademie.iaa.service.SeminarService;
import de.nordakademie.iaa.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/seminars")
public class SeminarController {

    private SeminarService seminarService;

    @Autowired
    public SeminarController(SeminarService seminarService) {
        this.seminarService = seminarService;
    }

    /**
     * List all seminars.
     *
     * @return the list of seminars.
     */
    @GetMapping
    public List<Seminar> listSeminars() {
        return seminarService.listSeminars();
    }

    /**
     * Saves the given seminar (either by creating a new one or updating an existing).
     *
     * @param seminar The seminar to save.
     */
    @PostMapping
    public ResponseEntity saveSeminar(@RequestBody Seminar seminar) {
        try {
            seminarService.saveSeminar(seminar);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Deletes the seminar with given id.
     *
     * @param id The id of the seminar to be deleted.
     */
    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity deleteSeminar(@PathVariable Long id) {
        try {
            seminarService.deleteSeminar(id);
            return ResponseEntity.ok(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
