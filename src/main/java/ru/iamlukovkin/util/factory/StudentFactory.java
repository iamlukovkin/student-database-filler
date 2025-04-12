package ru.iamlukovkin.util.factory;

import com.github.javafaker.Faker;
import ru.iamlukovkin.entity.Student;

import java.util.Random;

public class StudentFactory implements Creatable<Student> {

    private static StudentFactory instance;

    public static StudentFactory getInstance() {
        if (instance == null) {
            instance = new StudentFactory();
        }
        return instance;
    }

    private StudentFactory() {}

    @Override
    public Student create() {
        Faker faker = new Faker();
        Random random = new Random();
        boolean isMatch = random.nextBoolean();
        String actualAddress = faker.address().fullAddress();
        return Student.builder()
                .lastName(faker.name().lastName())
                .firstName(faker.name().firstName())
                .patronymic(faker.name().firstName())
                .snils(faker.number().digits(13))
                .inn(faker.number().digits(12))
                .certificateNumber(faker.number().digits(14))
                .passportSeries(faker.number().digits(4))
                .passportNumber(faker.number().digits(6))
                .actualAddress(actualAddress)
                .registrationAddress(isMatch ? actualAddress : faker.address().fullAddress())
                .temporaryAddress(isMatch ? actualAddress : faker.address().fullAddress())
                .mathResult(random.nextInt(51) + 50)
                .russianLanguageResult(random.nextInt(51) + 50)
                .profileSubjectResult(random.nextInt(51) + 50)
                .build();
    }
}
