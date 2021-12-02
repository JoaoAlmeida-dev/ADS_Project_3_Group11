package pt.iscte.asd.projectn3.group11.services.algorithms;

import pt.iscte.asd.projectn3.group11.models.Classroom;
import pt.iscte.asd.projectn3.group11.models.ClassCourse;

import java.util.List;

public interface IAlgorithmService {
    public void execute(List<ClassCourse> classes, List<Classroom> classrooms);
}