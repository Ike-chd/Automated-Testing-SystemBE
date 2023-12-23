package Services;

import DAOs.CourseDB;
import DAOs.DAOControllers.Courses.CourseDAO;
import DAOs.DAOControllers.Courses.ModuleDAO;
import DAOs.ModuleDB;
import Models.Courses.Course;
import Models.Courses.Module;
import Services.ServicesInterfaces.CourseService;
import java.util.List;

import java.util.Optional;

public class CourseHandler implements CourseService {

    private CourseDAO cdao = new CourseDB();
    private ModuleDAO mdao = new ModuleDB();

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
    public boolean deleteCourse(int courseId) {
        return cdao.deleteCourse(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return cdao.allCourses();
    }
    
    @Override
    public boolean updateModules(int courseID, List<Module> modules){
        cdao.deleteAllModulesInCourse(courseID);
        boolean allupdated = true;
        for (Module module : modules) {
            allupdated = allupdated && mdao.insertModuleIntoCourse(courseID, module.getModuleID());
        }
        return allupdated;
    }
}
