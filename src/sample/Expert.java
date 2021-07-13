package sample;

public class Expert extends User{
    private String address;
    private String specialization;
    private String login;
    private String password;

    public Expert(){} // конструктор по умолчанию
    public Expert(String firmname, String surname, String name, String patronymic, String telephone, String address,
                  String specialization, String login, String password){ // конструктор с параметрами
        super(firmname, surname, name, patronymic, telephone);
        this.address = address;
        this.specialization = specialization;
        this.login = login;
        this.password = password;
    }

    public void setNameFirmExpert(String firmname) { this.firmname = firmname; }
    public String getNameFirmExpert() { return firmname; }

    public void setSurnameExpert(String surname) { this.surname = surname; }
    public String getSurnameExpert() { return surname; }

    public void setNameExpert(String name) { this.name = name; }
    public String getNameExpert() { return name; }

    public void setPatronymicExpert(String patronymic) { this.patronymic = patronymic; }
    public String getPatronymicExpert() { return patronymic; }

    public void setTelephoneExpert(String telephone) { this.telephone = telephone; }
    public String getTelephoneExpert() { return telephone; }

    public void setAddress(String address) { this.address = address; }
    public String getAddress() { return address; }

    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getSpecialization() { return specialization; }

    public void setLogin(String login) { this.login = login; }
    public String getLogin() { return login; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }
}
