package de.nordakademie.iaa.dao;

import de.nordakademie.iaa.model.Seminar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for Seminars
 *
 * @author Arvid Ottenberg
 */
public interface SeminarDAO extends JpaRepository<Seminar,Long>,BaseDAO<Seminar, Long> {

    /**
     * Find a seminar by its title
     *
     * @param title title of the seminar
     * @return the seminar with the given title
     */
    Seminar findByTitle(String title);
}
