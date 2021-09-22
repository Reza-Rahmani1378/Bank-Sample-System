package ir.maktab.bank.util;


import ir.maktab.bank.util.input.Input;

import java.sql.Date;
import java.sql.Timestamp;

public class InputInformation {
    public static final String FIRSTNAME_REGEX = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$";
    public static final String LASTNAME_REGEX = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$";
    public static final String BIRTH_DAY_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public static final String NATIONAL_CODE_REGEX = "^\\d{10}$";
    public static final String PHONE_NUMBER_REGEX = "(0/91)?[7-9][0-9]{9}";
    public static final String CODE_BRANCH_REGEX = "[0-9]{4}";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,30}$";
    public static final String ACCOUNT_NUMBER_REGEX = "[0-9]{6}";
    public static final String CARD_NUMBER_REGEX = "[0-9]{16}";
    public static final String PASSWORD_CARD_REGEX = "[0-9]{4}";
    public static final String CVV2_CARD_REGEX = "[0-9]{4}";


    public static final String FIRSTNAME_WARNING =
            "The first name is between 1 and 25 characters.\n" +
                    "The first name can only start with an a-z (ignore case) character.\n" +
                    "After that the first name can contain a-z (ignore case) and [ '-,.].\n" +
                    "The first name can only end with an a-z (ignore case) character.";
    public static final String LASTNAME_WARNING =
            "The last name is between 1 and 25 characters.\n" +
                    "The last name can only start with an a-z (ignore case) character.\n" +
                    "After that the last name can contain a-z (ignore case) and [ '-,.].\n" +
                    "The last name can only end with an a-z (ignore case) character.";
    public static final String BIRTH_DAY_WARNING = "Your BirthDay is Wrong.";
    public static final String NATIONAL_CODE_WARNING =
            "Your national code must be 10 digit, at least one letter and one number.";
    public static final String PHONE_NUMBER_WARNING =
            "Your mobile number must be country specific.";
    public static final String PASSWORD_WARNING = "Password must contain at least one digit [0-9] ,\n" +
            "contain at least one lowercase Latin character [a-z] ,\n" +
            "contain at least one uppercase Latin character [A-Z] ,\n" +
            "contain at least one special character like ! @ # & ( ) and\n" +
            "contain a length of at least 8 characters and a maximum of 30 characters";

    public static final String ACCOUNT_NUMBER_WARNING = "Account Number Must Contains Only 6 Digits";
    public static final String CARD_NUMBER_WARNING = "Card Number Must Contains Only 16 Digits";
    public static final String PASS_WORD_CARD_WARNING = "Password Must Contains Only 4 Digits";
    public static final String CVV2_WARNING = "CVV2 Must Contains Only 4 Digits";


    public static final String CODE_BRANCH_WARNING = "Branch code must contains only 4 digits";
    public static final String FIRSTNAME_MESSAGE = "Enter your firstName :";
    public static final String LASTNAME_MESSAGE = "Enter your lastName :";
    public static final String BIRTH_DAY_MESSAGE = "Enter Your BirthDay in This format(2010-3-24) :";
    public static final String CODE_MESSAGE = "Enter your national code :";
    public static final String SALARY_MESSAGE = "Enter your salary :";
    public static final String PHONE_NUMBER_MESSAGE = "Enter your phone number :";
    public static final String STATE_MESSAGE = "Enter your state :";
    public static final String CITY_MESSAGE = "Enter your city :";
    public static final String POSTAL_ADDRESS_MESSAGE = "Enter your postal address :";
    public static final String POSTAL_CODE_MESSAGE = "Enter your postal code :";
    public static final String CODE_BRANCH_MESSAGE = "Enter code branch :";
    public static final String BRANCH_NAME_MESSAGE = "Enter branch name :";
    public static final String USER_NAME_MESSAGE = "Enter username :";
    public static final String PASSWORD_MESSAGE = "Enter password :";
    public static final String ACCOUNT_NUMBER_MESSAGE = "Enter account number :";
    public static final String CARD_NUMBER_MESSAGE = "Enter card number :";
    public static final String PASS_WORD_CARD_MESSAGE = "Enter card password :";
    public static final String CVV2_MESSAGE = "Enter card CVV2 :";


    public static String getFirstName() {
        return new Input(
                FIRSTNAME_MESSAGE,
                FIRSTNAME_WARNING,
                FIRSTNAME_REGEX,
                null).getInputString();
    }

    public static String getLastName() {
        return new Input(
                LASTNAME_MESSAGE,
                LASTNAME_WARNING,
                LASTNAME_REGEX,
                null).getInputString();
    }

    public static Date getBirthDay() {
        String birthday = new Input(
                BIRTH_DAY_MESSAGE,
                BIRTH_DAY_WARNING,
                BIRTH_DAY_REGEX,
                null).getInputString();
        return Date.valueOf(birthday);
    }

    public static Double getSalary() {
        return new Input(
                SALARY_MESSAGE,
                Double.MAX_VALUE,
                0.0,
                null).getInputDouble();
    }

    public static String getPhoneNumber() {
        return new Input(
                PHONE_NUMBER_MESSAGE,
                PHONE_NUMBER_WARNING,
                PHONE_NUMBER_REGEX,
                null
        ).getInputString();
    }

    public static String getState() {
        return new Input(
                STATE_MESSAGE
        ).getInputString();
    }

    public static String getCity() {
        return new Input(
                CITY_MESSAGE
        ).getInputString();
    }

    public static String getPostalAddress() {
        return new Input(
                POSTAL_ADDRESS_MESSAGE
        ).getInputString();
    }

    public static String getPostalCode() {
        return new Input(
                POSTAL_CODE_MESSAGE
        ).getInputString();
    }

    public static String getCodeBranch() {
        return new Input(
                CODE_BRANCH_MESSAGE,
                CODE_BRANCH_WARNING,
                CODE_BRANCH_REGEX,
                null
        ).getInputString();
    }

    public static String getNationalCode() {
        return new Input(
                CODE_MESSAGE,
                NATIONAL_CODE_WARNING,
                NATIONAL_CODE_REGEX,
                null
        ).getInputString();
    }

    public static String getBranchName() {
        return new Input(
                BRANCH_NAME_MESSAGE
        ).getInputString();
    }

    public static String getUsername() {
        return new Input(
                USER_NAME_MESSAGE
        ).getInputString();
    }

    public static String getPassword() {
        return new Input(
                PASSWORD_MESSAGE,
                PASSWORD_WARNING,
                PASSWORD_REGEX,
                null
        ).getInputString();
    }

    public static String getAccountNumber() {
        return new Input(
                ACCOUNT_NUMBER_MESSAGE,
                ACCOUNT_NUMBER_WARNING,
                ACCOUNT_NUMBER_REGEX,
                null
        ).getInputString();
    }

    public static String getCardNumber() {
        return new Input(
                CARD_NUMBER_MESSAGE,
                CARD_NUMBER_WARNING,
                CARD_NUMBER_REGEX,
                null
        ).getInputString();
    }

    public static String getPasswordCard() {
        return new Input(
                PASS_WORD_CARD_MESSAGE,
                PASS_WORD_CARD_WARNING,
                PASSWORD_CARD_REGEX,
                null
        ).getInputString();
    }

    public static String getCVV2() {
        return new Input(
                CVV2_MESSAGE,
                CVV2_WARNING,
                CVV2_CARD_REGEX,
                null).getInputString();
    }
}
