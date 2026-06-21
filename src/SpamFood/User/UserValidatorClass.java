package SpamFood.User;

public class UserValidatorClass {

    public static void validateUserName(String userName){
        if (userName == null || userName.trim().isEmpty()){
            throw new IllegalArgumentException("Username can not be empty!");
        }
        if (userName.contains(" ")){
            throw new IllegalArgumentException("Username can not contain Spaces!");
        }
    }
    public static void validatePassword(String password){
        if (password == null || password.length() < 8){
            throw new IllegalArgumentException("Password Should be 8 character at less");
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        for (int i = 0 ; i < password.length() ; i ++ ){
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)){
                hasUpperCase = true;
            }
            if (Character.isLowerCase(ch)){
                hasLowerCase = true;
            }
        }

        if(!hasUpperCase || !hasLowerCase){
            throw new IllegalArgumentException("Password Should has both Uppercase and lowercase letters");
        }
    }
    public static void validateConfirmPassword(String password ,String confirmPassword){
        if (confirmPassword == null || !confirmPassword.equals(password)){
            throw new IllegalArgumentException("Password don't Match. Try Again");
        }

    }

    public static void validatePhoneNumber(String phoneNumber){
        if (phoneNumber == null){
            return;
        }

        String trimmed = phoneNumber.trim();
        if (!trimmed.matches("0\\d{10}")){
            throw new IllegalArgumentException("Phone Number is invalid!");
        }
    }

    public static void validateAddress(String address){
        if (address.isEmpty()){
            throw new IllegalArgumentException("You must Enter Your Address");
        }
    }

}
