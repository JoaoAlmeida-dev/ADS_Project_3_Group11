package pt.iscte.asd.projectn3.group11.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.iscte.asd.projectn3.group11.models.ClassCourse;
import pt.iscte.asd.projectn3.group11.models.Classroom;
import pt.iscte.asd.projectn3.group11.models.util.metricCalculators.*;
import pt.iscte.asd.projectn3.group11.services.loaders.ClassCourseLoaderService;
import pt.iscte.asd.projectn3.group11.services.loaders.ClassroomLoaderService;

import java.util.Hashtable;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimetableEvaluationServiceTest {

	private static final String SAMPLE_CSV_FILE_CLASS_PATH = "./src/test/resources/classRoomTest.csv";
	private static final String SAMPLE_CSV_FILE_CLASS_FILLED_PATH = "src/test/resources/classTest-Filled.csv";

	//private LinkedList<MetricResult> metricResultList = new LinkedList<>();
	private static  Hashtable<String, Float> metricResultListTimetable;

	@BeforeAll
	static void setUp()
	{
		LinkedList<Classroom> classroomsTestList = ClassroomLoaderService.load(SAMPLE_CSV_FILE_CLASS_PATH);
		LinkedList<ClassCourse> classCoursesTestList = ClassCourseLoaderService.load(SAMPLE_CSV_FILE_CLASS_FILLED_PATH);

		metricResultListTimetable = TimetableEvaluationService.evaluateTimetable(classCoursesTestList, classroomsTestList);
		System.out.println(metricResultListTimetable);
	}


		@Test
	void AllocationMetricTest(){
		String metricName=AllocationMetric.class.getSimpleName();
		assertEquals(1.0f,metricResultListTimetable.get(metricName));
	}

	@Test
	void GoodCharacteristicsMetricTest(){
		String metricName=GoodCharacteristicsMetric.class.getSimpleName();
			assertEquals((float)Math.round(0.266667f*100)/100,(float)Math.round(metricResultListTimetable.get(metricName)*100)/100);
	}

	@Test
	void EnoughCapacityMetricTest(){
		String metricName=EnoughCapacityMetric.class.getSimpleName();
		assertEquals(0.8f,metricResultListTimetable.get(metricName));
	}

	@Test
	void RoomMovementsMetricTest(){
		String metricName=RoomMovementsMetric.class.getSimpleName();
		assertEquals(1f,metricResultListTimetable.get(metricName));
	}

	@Test
	void OverbookingMetricTest(){
		String metricName=OverbookingMetric.class.getSimpleName();
		assertEquals(0f,metricResultListTimetable.get(metricName));
	}

	@Test
	void UnderbookingMetricTest(){
		String metricName=UnderbookingMetric.class.getSimpleName();
		assertEquals(46f,metricResultListTimetable.get(metricName));
	}

	@Test
	void StudentClassMovementsMetricTest(){
		String metricName=StudentClassMovementsMetric.class.getSimpleName();
		assertEquals(2f,metricResultListTimetable.get(metricName));
	}
}