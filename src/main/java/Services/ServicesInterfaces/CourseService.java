package Services.ServicesInterfaces;

import Models.Courses.Course;
import Models.Courses.Module;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    public Optional<Course> getCourse(int courseId);

    public boolean addCourse(Course course);

    public boolean updateCourse(Course course);

    public boolean deleteCourse(int courseId);
    
    public List<Course> getAllCourses();

    public boolean updateModules(int courseId, List<Module> modules);
}
