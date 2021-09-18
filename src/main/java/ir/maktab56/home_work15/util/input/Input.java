package ir.maktab56.home_work15.util.input;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    private String message;
    private String warning;
    private String regex;
    private int floorInt;
    private int ceilInt;
    private double floorDouble;
    private double ceilDouble;
    private Long floorLong;
    private Long ceilLong;
    private List<String> unAllowedValues;
    private int inputInt;
    private Long inputLong;
    private String inputString;
    private Double inputDouble;
    private Scanner scanner = new Scanner(System.in);

    public Input(String message, String warning, String regex, List<String> unAllowedValues) {
        this.message = message;
        this.warning = warning;
        this.regex = regex;
        this.unAllowedValues = unAllowedValues;
    }

    public Input(String message) {
        this(message, "Input is not valid \n Please try again...", "", null);
    }

    public Input(String message, int ceilInt, int floorInt, List<String> unAllowedValues) {
        this.message = message;
        this.warning = "Input is not valid \n Please try again...";
        this.ceilInt = ceilInt;
        this.floorInt = floorInt;
        this.unAllowedValues = unAllowedValues;
    }

    public Input(String message, double ceilDouble, double floorDouble, List<String> unAllowedValues) {
        this.message = message;
        this.ceilDouble = ceilDouble;
        this.floorDouble = floorDouble;
        this.unAllowedValues = unAllowedValues;
    }


    public Input(String message, Long ceilLong, Long floorLong) {
        this.message = message;
        this.ceilLong = ceilLong;
        this.floorLong = floorLong;
    }


    public boolean validateInt() {
        if (floorInt != 0 && ceilInt != 0)
            return inputInt >= floorInt && inputInt <= ceilInt && !isUnAllowedValues(unAllowedValues);

        else
            return true;
    }

    public boolean validateDouble() {
        return inputDouble > floorDouble && inputDouble <= ceilDouble && !isUnAllowedValues(unAllowedValues);
    }

    public boolean validateLong() {
        return inputLong >= floorLong && inputLong <= ceilLong;
    }

    public boolean validateString() {
        return validationString() && !isUnAllowedValues(unAllowedValues);
    }

    public boolean validationString() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return matcher.find();
    }

    public boolean validateTextString() {
        floorInt = 10;
        ceilInt = 280;
        return inputString.length() >= floorInt && inputString.length() <= ceilInt && !isUnAllowedValues(unAllowedValues);
    }

    public boolean isUnAllowedValues(List<String> unAllowedValues) {
        if (unAllowedValues == null)
            return false;
        for (String unAllowedValue : unAllowedValues) {
            if (unAllowedValue.equals(inputString))
                return true;
        }
        return false;
    }

    public void setInputInt() {
        inputInt = scanner.nextInt();
    }

    public int getInputInt() {
        while (true) {
            System.out.println(message);
            setInputInt();
            if (validateInt()) {
                return inputInt;
            } else
                System.out.println(warning);
        }
    }

    public void setInputDouble() {
        inputDouble = scanner.nextDouble();
    }

    public double getInputDouble() {
        while (true) {
            System.out.println(message);
            setInputDouble();
            if (validateDouble()) {
                return inputDouble;
            } else
                System.out.println(warning);
        }
    }

    public void setInputLong() {
        inputLong = scanner.nextLong();
    }

    public Long getInputLong() {
        while (true) {
            System.out.println(message);
            setInputLong();
            if (validateLong()) {
                return inputLong;
            } else
                System.out.println(warning);
        }
    }

    public void setInputString() {
        System.out.println(message);
        inputString = scanner.nextLine();
    }

    public String getInputString() {

        while (true) {
            setInputString();
            if (validateString()) {
                return inputString;
            } else
                System.out.println(warning);
        }
    }

    public void setInputTextString() {
        System.out.println(message);
        inputString = scanner.nextLine();
    }

    public String getInputTextString() {
        while (true) {

            setInputTextString();
            if (validateTextString()) {
                return inputString;
            } else
                System.out.println(warning);
        }
    }

}
