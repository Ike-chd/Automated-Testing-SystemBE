package Services;

import DAOs.CourseDB;
import DAOs.DAOControllers.Courses.CourseDAO;
import Models.Courses.Course;
import Services.ServicesInterfaces.CourseService;

import java.util.Optional;

public class CourseHandler implements CourseService {
    private CourseDAO cdao = new CourseDB();
    @Override
    public Optional<Course> getCourse(int courseId) {
        return Optional.ofNullable(cdao.getCourse(courseId));
    }
    
    @Override
    public boolean addCourse(Course course) {
        return cdao.insertCourse(course);
    }
    
    @Override
    public boolean updateCourse(Course course) {
        return cdao.updateCourse(course);
    }
    
    @Override
    public boolean deleteCourse(Course course) {
        return cdao.deleteCourse(course);
    }
}
