package de.nordakademie.iaa.controller;

import de.nordakademie.iaa.model.Century;
import de.nordakademie.iaa.service.CenturyService;
import de.nordakademie.iaa.service.exception.NotEnoughChangeoverTimeProvidedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * REST-Controller for century entities.
 *
 * @author Timo Schlatter
 */

@Transactional
@RestController
@RequestMapping("/centuries")
public class CenturyController {

    private CenturyService centuryService;

    @Autowired
    public CenturyController(CenturyService centuryService) {
        this.centuryService = centuryService;
    }

    /**
     * Updates the century with given id.
     *
     * @param id      identifier for century to update.
     * @param century new values for century.
     * @return status OK or BAD_REQUEST (if update failed or no century was found for given id).
     */
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity updateCentury(@PathVariable Long id, @RequestBody Century century) {
        try {
            if (centuryService.loadCentury(id) != null) {
                centuryService.saveCentury(century);
                return ResponseEntity.ok().build();
            }
        } catch (NotEnoughChangeoverTimeProvidedException ignored) {
        }
        return ResponseEntity.badRequest().build();
    }
}
