package sample.database;


/*          ИНСТРУКЦИЯ
    Класс предназначен для создания констант, чтобы их использовать множество раз при обращении к столбцам в БД
*/


public class constant {

    public static final String DATABASE_EXPERT_TABLE = "expert";

    public static final String EXPERT_ID = "id";
    public static final String EXPERT_NAMEFIRM = "namefirm";
    public static final String EXPERT_SURNAME = "surname";
    public static final String EXPERT_NAME = "name";
    public static final String EXPERT_PATRONYMIC = "patronymic";
    public static final String EXPERT_ADDRESS = "address";
    public static final String EXPERT_TELEPHONE = "telephone";
    public static final String EXPERT_SPECIALIZATION = "specialization";
    public static final String EXPERT_LOGIN = "login";
    public static final String EXPERT_PASSWORD = "password";

    public static final String DATABASE_SELLER_TABLE = "seller";

    public static final String SELLER_ID = "id";
    public static final String SELLER_NAMEFIRM = "namefirm";
    public static final String SELLER_SURNAME = "surname";
    public static final String SELLER_NAME = "name";
    public static final String SELLER_PATRONYMIC = "patronymic";
    public static final String SELLER_TELEPHONE = "telephone";
    public static final String SELLER_LOT = "lot";

    public static final String DATABASE_LOT_TABLE = "lot";

    public static final String LOT_ID = "id";
    public static final String LOT_TYPE = "type";
    public static final String LOT_NAME = "name";
    public static final String LOT_STARTPRICE = "startprice";
    public static final String LOT_YEAR = "year";
}
