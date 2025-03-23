package com.example.creditapp.models;

import jakarta.persistence.*;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Полное имя обязательно")
    @Length(min = 2, max = 255, message = "Полное имя должно содержать от 2 до 255 символов")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Паспорт обязателен")
    @Pattern(regexp = "\\d{4} \\d{6}", message = "Неверный формат паспорта (например, 1234 567890)")
    @Column(name = "passport")
    private String passport;

    @NotBlank(message = "Пол обязателен")
    @Pattern(regexp = "Мужской|Женский", message = "Пол должен быть 'Мужской' или 'Женский'")
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "Семейное положение обязательно")
    @Pattern(regexp = "Женат|Замужем|Холост|Не замужем", message = "Укажите корректное семейное положение: Женат, Замужем, Холост, Не замужем")
    @Column(name = "marital_status")
    private String maritalStatus;

    @NotBlank(message = "Адрес проживания обязателен")
    @Length(min = 5, max = 500, message = "Адрес проживания должен содержать от 5 до 500 символов")
    @Column(name = "residential_address")
    private String residentialAddress;

    @NotBlank(message = "Адрес регистрации обязателен")
    @Length(min = 5, max = 500, message = "Адрес регистрации должен содержать от 5 до 500 символов")
    @Column(name = "registration_address")
    private String registrationAddress;

    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "\\+\\d{11}", message = "Неверный формат телефона (например, +79991234567)")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Период работы обязателен")
    @Length(min = 2, max = 50, message = "Период работы должен содержать от 2 до 50 символов")
    @Column(name = "employment_period")
    private String employmentPeriod;

    @NotBlank(message = "Должность обязательна")
    @Length(min = 2, max = 50, message = "Должность должна содержать от 2 до 50 символов")
    @Column(name = "position")
    private String position;

    @NotBlank(message = "Организация обязательна")
    @Length(min = 2, max = 100, message = "Название организации должно содержать от 2 до 100 символов")
    @Column(name = "organization")
    private String organization;

    @NotNull(message = "Рейтинг кредитной истории обязателен")
    @Min(value = 0, message = "Рейтинг кредитной истории не может быть меньше 0")
    @Max(value = 100, message = "Рейтинг кредитной истории не может быть больше 100")
    @Column(name = "credit_history_rating")
    private Integer creditHistoryRating;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmploymentPeriod() {
        return employmentPeriod;
    }

    public void setEmploymentPeriod(String employmentPeriod) {
        this.employmentPeriod = employmentPeriod;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getCreditHistoryRating() {
        return creditHistoryRating;
    }

    public void setCreditHistoryRating(Integer creditHistoryRating) {
        this.creditHistoryRating = creditHistoryRating;
    }
}
