package peaksoft.springbootlesson.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlesson.dto.CompanyRequest;
import peaksoft.springbootlesson.dto.CompanyResponse;
import peaksoft.springbootlesson.dto.CourseRequest;
import peaksoft.springbootlesson.dto.CourseResponse;
import peaksoft.springbootlesson.entity.Course;
import peaksoft.springbootlesson.service.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/courses")
@Tag(name = "Course Auth", description = "We can create new Course")
public class CourseController {
    private final CourseService courseService;
    @PostMapping
    @Operation(summary = "Create", description = "Admin can create new Course")
    public CourseResponse create(@RequestBody CourseRequest request){
        return  courseService.create(request);
    }
    @GetMapping()
    @Operation(summary = "Get all courses", description = "Only Admin get all Courses")
    public List<CourseResponse> getAll(){
        return courseService.getAllCourses();
    }
    @GetMapping("{id}")
    @Operation(summary = "Get by id", description = "Admin can get Course by id")
    public CourseResponse getCourseById(@PathVariable("id")Long id){
        return courseService.getCourseById(id);
    }
    @PutMapping("{id}")
    @Operation(summary = "Update", description = "Admin can update Course")
    public CourseResponse update(@PathVariable("id")Long id,@RequestBody CourseRequest request){
        return courseService.updateCourse(id,request);
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Delete", description = "Admin can delete Course by id")
    public String delete(@PathVariable("id")Long id){
        courseService.deleteCourse(id);
        return "Successfully deleted course with id: "+id;
    }
}
