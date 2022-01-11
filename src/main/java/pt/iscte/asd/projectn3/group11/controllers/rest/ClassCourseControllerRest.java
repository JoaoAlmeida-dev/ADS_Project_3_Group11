package pt.iscte.asd.projectn3.group11.controllers.rest;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pt.iscte.asd.projectn3.group11.models.ClassCourse;
import pt.iscte.asd.projectn3.group11.models.MetricResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static pt.iscte.asd.projectn3.group11.services.controllerhandlers.ClassCourseControllerHandler.*;

@RestController
public class ClassCourseControllerRest {

    //region PATH_CONSTANTS
    public static final String CLASSES_REST_PATH = "/rest/classes";
    //endregion

    //region TIMETABLE

    /**
     * Gets the Classes.
     * @param response response
     * @param request request
     * @return List of classes.
     */
    @GetMapping(value = CLASSES_REST_PATH)
    public List<ClassCourse.ClassCourseJson> getClasses(HttpServletResponse response, HttpServletRequest request) {
        return getClassesHandler(response, request);
    }

    /**
     * Sets classrooms.
     * @param response response
     * @param request request
     */
    @PostMapping(value = CLASSES_REST_PATH, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity setClasses(HttpServletResponse response, HttpServletRequest request, MultipartFile classesFile) {
        return setClassesHandler(response, request, classesFile);
    }

    //endregion

    //region METRIC_RESULTS
    /**
     * Gets the metrics.
     * @param response response
     * @param request request
     * @return The metrics.
     */
    @GetMapping(value = CLASSES_REST_PATH + "/metrics")
    public List<MetricResult> getMetricResults(HttpServletResponse response, HttpServletRequest request) {
        return getMetricResultsHandler(response, request);
    }

    //endregion

    //region TIMETABLE_DOWNLOAD

    /**
     * Downloads classes.
     * @param response response
     * @param request request
     * @return File
     */
    @GetMapping(value = CLASSES_REST_PATH + "/download")
    public ResponseEntity<Resource> downloadClasses(HttpServletResponse response, HttpServletRequest request) {
        return downloadClassesHandler(response, request);
    }

    //endregion
}
