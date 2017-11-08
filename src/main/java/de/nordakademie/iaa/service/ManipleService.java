package de.nordakademie.iaa.service;

import de.nordakademie.iaa.model.Maniple;
import de.nordakademie.iaa.service.exception.NotEnoughChangeoverTimeProvidedException;

import java.util.List;


public interface ManipleService {

    /**
     * Stores the given maniple into the database.
     *
     * @param maniple the maniple to be saved.
     */
    void saveManiple(Maniple maniple) throws NotEnoughChangeoverTimeProvidedException;

    /**
     * List all maniples currently stored in the database.
     *
     * @return a list of Maniple entities. If no maniple was found an empty list is returned.
     */
    List<Maniple> listManiples();

    /**
     * Returns the maniple identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    Maniple loadManiple(Long id);

    /**
     * Deletes the maniple with the given id.
     *
     * @param id The identifier.
     * @return
     */
    boolean deleteManiple(Long id);

    /**
     * Retrieves a maniple by its name.
     * @param name the identifier.
     * @return the found maniple or null if no maniple was found with given identifier.
     */
    Maniple findByName(String name);
}
