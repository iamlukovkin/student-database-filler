package ru.iamlukovkin.util.factory;

import com.github.javafaker.Faker;
import ru.iamlukovkin.entity.ParentInformation;

public class ParentInformationFactory implements Creatable<ParentInformation> {

    private static ParentInformationFactory instance;

    public static ParentInformationFactory getInstance() {
        if (instance == null) {
            instance = new ParentInformationFactory();
        }
        return instance;
    }

    private ParentInformationFactory() {}

    @Override
    public ParentInformation create() {
        Faker faker = new Faker();
        ParentInformation parentInformation = new ParentInformation();
        parentInformation.setPassportSeries(faker.number().digits(4));
        parentInformation.setPassportNumber(faker.number().digits(6));
        parentInformation.setLastName(faker.name().lastName());
        parentInformation.setFirstName(faker.name().firstName());
        parentInformation.setPatronymic(faker.name().firstName());
        parentInformation.setPhoneNumber(faker.phoneNumber().phoneNumber());
        return parentInformation;
    }
}
