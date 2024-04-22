package kma.databases.entities;

public class CustomerCard {

    private String number;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
    private Long percent;

    public static class Builder implements IBuilder<CustomerCard> {

        CustomerCard customerCard = new CustomerCard();

        public Builder setNumber(String num) {
            customerCard.number = num;
            return this;
        }

        public Builder setSurname(String surname) {
            customerCard.surname = surname;
            return this;
        }

        public Builder setName(String name) {
            customerCard.name = name;
            return this;
        }

        public Builder setPatronymic(String patronymic) {
            customerCard.patronymic = patronymic;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            customerCard.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setCity(String city) {
            customerCard.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            customerCard.street = street;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            customerCard.zipCode = zipCode;
            return this;
        }

        public Builder setPercent(Long percent) {
            customerCard.percent = percent;
            return this;
        }

        @Override
        public CustomerCard build() {
            return customerCard;
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }
}
