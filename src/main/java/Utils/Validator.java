package Utils;

public abstract class Validator {
    enum CONSTRAINS {
        MIN_EMAIL_SIZE(3),
        MIN_USERNAME_SIZE(3),
        MAX_USERNAME_SIZE(20),
        MIN_PASSWORD_SIZE(8);

        private int value;

        CONSTRAINS(int value) {
            this.value = value;
        }

        protected int getValue() {
            return value;
        }
    }

    public static boolean emailValidate(String email) {
        int len = email.length();
        if (len < CONSTRAINS.MIN_EMAIL_SIZE.getValue() || email.isBlank()) return false;

        int atCount = 0;


        for (int i = 0; i < len; i++) {
            if (email.charAt(i) == '@' && (i != 1 && i != len - 1)) atCount++;
        }

        return atCount == 1;
    }

    //username debe ser de 3 a 20 caracteres,debe contener
    public static boolean usernameValidate(String username) {
        if (username.isBlank()) return false;

        int len = username.length();
        return len >= CONSTRAINS.MIN_USERNAME_SIZE.getValue() && len <= CONSTRAINS.MAX_USERNAME_SIZE.getValue();
    }

    //password debe ser minimo 8 caracteres y contener una MAYUSCULA, una minuscula y un numero
    public static boolean passwordValidate(String password) {
        if (password.isBlank()) return false;

        int len = password.length();
        if (len < CONSTRAINS.MIN_PASSWORD_SIZE.getValue()) return false;

        int mayuscula = 0, minuscula = 0, numero = 0;
        for (int i = 0; i < len; i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) mayuscula++;
            if (Character.isLowerCase(c)) minuscula++;
            if (Character.isDigit(c)) numero++;
        }

        return mayuscula > 0 && minuscula > 0 && numero > 0;
    }
}
