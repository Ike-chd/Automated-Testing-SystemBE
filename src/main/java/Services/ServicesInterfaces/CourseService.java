package Services.ServicesInterfaces;

import Models.Courses.Course;
import java.util.Optional;

public interface CourseService {

    public Optional<Course> getCourse(int courseId);

    public boolean addCourse(Course course);

    public boolean updateCourse(Course course);

    public boolean deleteCourse(Course course);
}
