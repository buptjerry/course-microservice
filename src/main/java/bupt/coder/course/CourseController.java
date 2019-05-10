package bupt.coder.course;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PutMapping
    @ApiOperation("增")
    public void add(@RequestBody Course course) {
        courseRepository.saveAndFlush(course);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删")
    public void delete(@PathVariable long id) {
        Course course = courseRepository.getOne(id);
        courseRepository.delete(course);
    }

    @PostMapping("/{id}")
    @ApiOperation("改")
    public void update(@RequestBody Course course, @PathVariable long id) {
        Course old = courseRepository.getOne(id);
        old.setId(course.getId());
        old.setEndTime(course.getEndTime());
        old.setName(course.getName());
        old.setPlace(course.getPlace());
        old.setStartTime(course.getStartTime());
        old.setTeacherId(course.getTeacherId());
        courseRepository.saveAndFlush(old);
    }

    @GetMapping("/{id}")
    @ApiOperation("查指定课程")
    public Course find(@PathVariable long id) {
        return courseRepository.getOne(id);
    }

    @GetMapping
    @ApiOperation("查所有课程")
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

}
