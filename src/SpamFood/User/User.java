package SpamFood.User;


public class User{
    private String userName;
    private String password;
    private String phoneNumber;
    private String address;

    //.......................... UserName ...................//
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        UserValidatorClass.validateUserName(userName);
        this.userName = userName;
    }

    //.......................... Password ........................//
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        UserValidatorClass.validatePassword(password);
        this.password = password;
    }

    //............................. PhoneNumber .......................//
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        UserValidatorClass.validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    //.............................Address.................................//
    public String getAddress(){return address;}

    public void setAddress(String address){
        UserValidatorClass.validateAddress(address);
        this.address = address;
    }
    //...........................  SpamFood.User Constructor ...................... //

    public User(String userName , String password, String phoneNumber){
        setUserName(userName);
        setPassword(password);
        setPhoneNumber(phoneNumber);
    }

    public User(String userName , String password){
        this.userName = userName;
        this.password = password;
        this.phoneNumber = "";
    }

    public User(String address){
        this.address = address;
    }
}