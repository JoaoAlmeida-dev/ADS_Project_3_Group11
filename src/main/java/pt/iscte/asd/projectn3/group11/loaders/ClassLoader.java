package pt.iscte.asd.projectn3.group11.loaders;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.multipart.MultipartFile;
import pt.iscte.asd.projectn3.group11.models.Class;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

public class ClassLoader {
    //region LOADERS

    public final static LinkedList<Class> classes = new LinkedList<>();

    /**
     * Loads a Class csv file from given path.
     *
     * @param path path to the classroom csv.
     * @return List of classrooms
     */
    public static final LinkedList<Class> load(final String path) {
        try (
                final Reader reader = Files.newBufferedReader(Paths.get(path));
                final CSVReader csvReader = new CSVReader(reader)
        ) {
            extractClass(csvReader);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * Loads a Class csv file.
     *
     * @param file object containing the csv for the Classes.
     * @return List of classrooms
     */
    public static final LinkedList<Class> load(final File file) {
        try (
                final Reader reader = new FileReader(file);
                final CSVReader csvReader = new CSVReader(reader)
        ) {
            extractClass(csvReader);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return classes;
        }
        return classes;
    }

    /**
     * Loads a Class csv file.
     *
     * @param multipartFile object containing the csv for the Classes.
     * @return List of classrooms
     */
    public static final LinkedList<Class> load(final MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        ClassLoader.load(file);
        return classes;
    }

    private static void extractClass(CSVReader csvReader) throws IOException, CsvValidationException {
        csvReader.readNext();
        String[] nextRecord;

        while ((nextRecord = csvReader.readNext()) != null) {

            final String course = nextRecord[0];
            final String unit = nextRecord[1];
            final String shift = nextRecord[2];
            final String classOfCourse = nextRecord[3];
            final String studentNum = nextRecord[4];
            final String shiftsWithFreeSpots = nextRecord[5];
            final String shiftsWithMoreThanTheCapacity = nextRecord[6];
            final String weekday = nextRecord[7];
            final String beginningHour = nextRecord[8];
            final String endHour = nextRecord[9];
            final String monthDay = nextRecord[10];
            final String askedCharacteristics = nextRecord[11];
            final String classroom = nextRecord[12];
            final String capacity = nextRecord[13];
            final String realCharacteristics = nextRecord[14];

            Class aClass = new Class.Builder().
                    courses(Arrays.asList(course.split(", "))).
                    units(Arrays.asList(unit.split(", "))).
                    shift(shift).
                    classesOfCourse(Arrays.asList(classOfCourse.split(", "))).
                    numberOfStudentsInClass(Integer.parseInt(studentNum)).
                    shiftsWithFreeSpots(Integer.parseInt(shiftsWithFreeSpots)).
                    shiftsWithMoreThanTheCapacity(Integer.parseInt(shiftsWithMoreThanTheCapacity)).
                    weekday(weekday).
                    beginningHour(beginningHour).
                    endHour(endHour).
                    monthDay(monthDay).
                    askedCharacteristics(askedCharacteristics).
                    classroom(classroom).
                    capacity(capacity).
                    realCharacteristics(Arrays.asList((realCharacteristics.split(", ")))).
                    build();

            System.out.println(aClass);
            classes.add(aClass);
        }
    }

    public static void clear() {
        ClassLoader.classes.clear();
    }

    //endregion
}
