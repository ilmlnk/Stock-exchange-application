package sample;

public abstract class User { /*    абстрактный класс     */
    protected String firmname;
    protected String surname;
    protected String name;
    protected String patronymic;
    protected String telephone;

    public User() {} // конструктор по умолчанию
    public User(String firmname, String surname, String name, String patronymic, String telephone){ // конструктор с параметрами
        this.firmname = firmname;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.telephone = telephone;
    }

}
