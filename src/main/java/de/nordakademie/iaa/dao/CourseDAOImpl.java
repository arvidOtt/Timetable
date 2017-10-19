package de.nordakademie.iaa.dao;

import de.nordakademie.iaa.model.Course;
import de.nordakademie.iaa.model.Room;

import java.util.List;

/**
 * Created by arvid on 19.10.17.
 */
public class CourseDAOImpl extends DAO<Course> implements CourseDAO {
    @Override
    public Course findCourseByFieldAndNumber(String field, int number) {
        List<Course> courses = entityManager.createQuery(
                "select c from Course c where c.field = :field " +
                        "and c.number = :number")
                .setParameter("field", field)
                .setParameter("number", number)
                .getResultList();
        if(courses.isEmpty()){
            return null;
        }
        else return courses.get(0);
    }
}
