package de.nordakademie.iaa.controller;


import de.nordakademie.iaa.model.Cohort;
import de.nordakademie.iaa.model.Maniple;
import de.nordakademie.iaa.service.CohortService;
import de.nordakademie.iaa.service.EventService;
import de.nordakademie.iaa.service.ManipleService;
import de.nordakademie.iaa.service.exception.NotEnoughChangeoverTimeProvidedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Transactional
@RestController
@RequestMapping("/cohorts")
public class CohortController {

    private EventService eventService;
    private CohortService cohortService;
    private ManipleService manipleService;

    @Autowired
    public CohortController(EventService eventService, CohortService cohortService, ManipleService manipleService) {
        this.eventService = eventService;
        this.cohortService = cohortService;
        this.manipleService = manipleService;
    }

    /**
     * List all cohorts.
     *
     * @return the list of cohorts.
     */
    @GetMapping
    public List<Cohort> listCohorts() {
        return cohortService.listCohorts();
    }

    /**
     * Saves the given cohort.
     *
     * @param cohort The cohort to save.
     */
    @PostMapping
    public ResponseEntity saveCohort(@RequestBody Cohort cohort) {
        try {
            if (cohortService.findByName(cohort.getName()) == null) {
                cohortService.saveCohort(cohort);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception ignored) {
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Updates the given cohort.
     *
     * @param cohort The cohort to update.
     */
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity updateCohort(@PathVariable Long id, @RequestBody Cohort cohort) {
        try {
            if (cohortService.loadCohort(id) != null) {
                cohortService.saveCohort(cohort);
                return ResponseEntity.ok().build();
            }
        } catch (Exception ignored) {
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Deletes the cohort with given id.
     *
     * @param id The id of the cohort to be deleted.
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity deleteCohort(@PathVariable Long id) {
        Cohort cohort = cohortService.loadCohort(id);
        if (cohort != null) {
            cohort.getManiples().forEach(maniple -> {
                maniple.getCenturies().forEach(eventService::deleteEventsByGroup);
                eventService.deleteEventsByGroup(maniple);
            });
            eventService.deleteEventsByGroup(cohort);
            cohortService.deleteCohort(id);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Saves the given maniple.
     * Adds the given maniple to the referenced cohort.
     *
     * @param maniple The century to save.
     */
    @RequestMapping(value = "/{id}/addManiple", method = POST)
    public ResponseEntity addManiple(@PathVariable Long id, @RequestBody Maniple maniple) {
        Cohort cohort = cohortService.loadCohort(id);
        if (cohort != null) {
            String newCenturyName = maniple.getName() + cohort.getName();
            if (manipleService.findByName(newCenturyName) == null) {
                try {
                    maniple.setName(newCenturyName);
                    manipleService.saveManiple(maniple);
                    cohort.addManiple(maniple);
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                } catch (NotEnoughChangeoverTimeProvidedException ignored) {
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Deletes the maniple with the given id.
     *
     * @param cohortId  The id of the cohort, to which the maniple belongs.
     * @param manipleId The id of the maniple to delete.
     */
    @RequestMapping(value = "/{cohortId}/deleteManiple/{manipleId}", method = DELETE)
    public ResponseEntity removeManiple(@PathVariable Long cohortId, @PathVariable Long manipleId) {
        Cohort cohort = cohortService.loadCohort(cohortId);
        Maniple maniple = manipleService.loadManiple(manipleId);
        if (cohort != null && maniple != null) {
            cohort.removeManiple(maniple);
            maniple.getCenturies().forEach(eventService::deleteEventsByGroup);
            eventService.deleteEventsByGroup(maniple);
            manipleService.deleteManiple(maniple.getId());
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().build();
    }
}