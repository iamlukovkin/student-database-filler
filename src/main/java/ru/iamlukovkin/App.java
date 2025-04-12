package ru.iamlukovkin;

import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.iamlukovkin.entity.*;
import ru.iamlukovkin.entity.primaryKeys.StudentAndParentKey;
import ru.iamlukovkin.entity.primaryKeys.StudentEmailKey;
import ru.iamlukovkin.entity.primaryKeys.StudentTelephoneKey;
import ru.iamlukovkin.entity.primaryKeys.StudyKey;
import ru.iamlukovkin.util.factory.ParentInformationFactory;
import ru.iamlukovkin.util.factory.StudentFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {

    private static SessionFactory sessionFactory = null;
    private static StudentFactory studentFactory = null;
    private static Faker faker;
    private static ParentInformationFactory parentInformationFactory = null;
    private static Integer requiredStudentsCount = 100000;
    private static Integer telephoneNumbersCount = 5;
    private static Integer emailNumbersCount = 3;

    public static void inputVariables() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter REQUIRED_STUDENTS_COUNT: ");
        requiredStudentsCount = scanner.nextInt();

        System.out.print("Enter TELEPHONE_NUMBERS_COUNT: ");
        telephoneNumbersCount = scanner.nextInt();

        System.out.print("Enter EMAIL_NUMBERS_COUNT: ");
        emailNumbersCount = scanner.nextInt();

        System.out.println("\n--- Input Summary ---");
        System.out.println("REQUIRED_STUDENTS_COUNT: " + requiredStudentsCount);
        System.out.println("TELEPHONE_NUMBERS_COUNT: " + telephoneNumbersCount);
        System.out.println("EMAIL_NUMBERS_COUNT: " + emailNumbersCount);

        scanner.close();
    }

    private static void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        faker = new Faker();
        studentFactory = StudentFactory.getInstance();
        parentInformationFactory = ParentInformationFactory.getInstance();
    }

    private static void tearDown() {
        sessionFactory.close();
    }

    public static void main( String[] args ) {
        setUp();
        inputVariables();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        deleteRows(session);
        int counter = 1;
        while (counter <= requiredStudentsCount) {
            try {
                transaction = session.beginTransaction();
                System.out.println("BEGIN TRANSACTION #" + counter + ";");
                Student student = createStudent(session);
                boolean isGuardian = faker.random().nextBoolean();
                ParentInformation father = createParent(student, isGuardian, true, session);
                StudentAndParent fatherLink = createStudentAndParent(student, father, isGuardian, true, session);
                ParentInformation mother = createParent(student, isGuardian, false, session);
                StudentAndParent motherLink = createStudentAndParent(student, mother, isGuardian, false, session);
                List<StudentEmail> studentEmails = createStudentEmails(session, student);
                List<Study> studies = createStudy(session, student);
                List<StudentTelephone> studentTelephones = createStudentTelephones(session, student);
                transaction.commit();
                System.out.println("CREATED: {" +
                        "\n\tstudent: {" + student + "};" +
                        "\n\temails: {" + Arrays.toString(studentEmails.stream().map(StudentEmail::getEmail).toArray()) + "};" +
                        "\n\ttelephones: {" + Arrays.toString(studentTelephones.stream().map(StudentTelephone::getPhoneNumber).toArray()) + "};" +
                        "\n\tmother: {" + mother + "};" +
                        "\n\tmotherLink: {" + motherLink + "};" +
                        "\n\tstudentGroups: {" + Arrays.toString(studies.stream().map(study -> study.getId().getGroupNumber()).toArray()) + "};" +
                        "\n\tfather: {" + father + "};" +
                        "\n\tfatherLink: {" + fatherLink + "};" +
                        "\n}" +
                        "\n------------------------------------------------");
                counter++;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        tearDown();
    }

    private static void deleteRows(Session session) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM StudentAndParent").executeUpdate();
        session.createQuery("DELETE FROM ParentInformation").executeUpdate();
        session.createQuery("DELETE FROM StudentEmail").executeUpdate();
        session.createQuery("DELETE FROM StudentTelephone ").executeUpdate();
        session.createQuery("DELETE FROM Study").executeUpdate();
        session.createQuery("DELETE FROM Student").executeUpdate();
        transaction.commit();
    }

    private static ParentInformation createParent(Student student, boolean isGuardian, boolean isFather, Session session) {
        ParentInformation parent = parentInformationFactory.create();
        if (!isGuardian && isFather) parent.setFirstName(student.getPatronymic());
        if (!isGuardian) parent.setLastName(student.getLastName());
        session.persist(parent);
        session.flush();
        return parent;

    }

    private static Student createStudent(Session currentSession) {
        Student student = studentFactory.create();
        currentSession.persist(student);
        currentSession.flush();
        return student;
    }

    private static List<StudentEmail> createStudentEmails(Session session, Student student) {
        List<StudentEmail> emails = new ArrayList<>();
        for (int i = 1; i <= emailNumbersCount; i++) {
            StudentEmailKey key = new StudentEmailKey(student.getId(), i);
            StudentEmail email = new StudentEmail(key, student, faker.internet().emailAddress());
            session.persist(email);
            session.flush();
            emails.add(email);
        }
        return emails;
    }

    private static List<StudentTelephone> createStudentTelephones(Session session, Student student) {
        List<StudentTelephone> telephones = new ArrayList<>();
        for (int i = 1; i <= telephoneNumbersCount; i++) {
            StudentTelephoneKey key = new StudentTelephoneKey(student.getId(), i);
            StudentTelephone telephone = new StudentTelephone(key, student, faker.phoneNumber().phoneNumber());
            session.persist(telephone);
            session.flush();
            telephones.add(telephone);
        }
        return telephones;
    }

    private static StudentAndParent createStudentAndParent(Student student, ParentInformation parent, boolean isGuardian, boolean isFather, Session session) {
        StudentAndParentKey key = new StudentAndParentKey(student.getId(), parent.getId());
        String whoIs = isGuardian ? "Guardian" :
                (isFather ? "Father" : "Mother");
        StudentAndParent studentAndParent = new StudentAndParent(key, student, parent, whoIs);
        session.persist(studentAndParent);
        session.flush();
        return studentAndParent;
    }

    private static List<Study> createStudy(Session session, Student student) {
        List<Study> studies = new ArrayList<>();
        StudyKey key = new StudyKey(student.getId(), Integer.toString(200 + faker.number().numberBetween(0, 50)));
        Study fullTimeStudy = new Study(key, student, true, faker.random().nextBoolean(), false);
        session.persist(fullTimeStudy);
        session.flush();
        studies.add(fullTimeStudy);
        if (faker.random().nextBoolean()) {
            StudyKey secondKey = new StudyKey(student.getId(), (200 + faker.number().numberBetween(0, 50)) + "M");
            Study commercialStudy = new Study(secondKey, student, false, false, true);
            session.persist(commercialStudy);
            session.flush();
            studies.add(commercialStudy);
        }
        return studies;
    }
}
