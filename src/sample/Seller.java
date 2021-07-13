package sample;

public class Seller extends User {
    private String lot;

    public Seller(){}
    public Seller(String firmname, String surname, String name, String patronymic, String telephone, String lot){
        super(firmname, surname, name, patronymic, telephone);
        this.lot = lot;
    }

    public void setNameFirmSeller(String firmname) { this.firmname = firmname; }
    public String getNameFirmSeller() { return firmname; }

    public void setSurnameSeller(String surname) { this.surname = surname; }
    public String getSurnameSeller() { return surname; }

    public void setNameSeller(String name) { this.name = name; }
    public String getNameSeller() { return name; }

    public void setPatronymicSeller(String patronymic) { this.patronymic = patronymic; }
    public String getPatronymicSeller() { return patronymic; }

    public void setTelephoneSeller(String telephone) { this.telephone = telephone; }
    public String getTelephoneSeller() { return telephone; }

    public void setLot(String lot) { this.lot = lot; }
    public String getLot() { return lot; }
}
