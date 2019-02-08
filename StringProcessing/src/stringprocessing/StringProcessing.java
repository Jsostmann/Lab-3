package stringprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author jamesostmann
 */
public class StringProcessing {

    public static void main(String[] args) {

        readRecords("records.txt");

    }

    public static void readRecords(String fName) {

        String[] records = new String[97];

        try {

            Scanner scan = new Scanner(new File(fName));
            int i = 0;

            while (scan.hasNextLine()) {

                String tempLine = scan.nextLine();
                tempLine = tempLine.trim();
                String[] tempRecord = tempLine.split(",");

                if (recordIsGood(tempRecord)) {
                    String output = String.format("%-20s%-20s%-15s%-15d%-20s%-20s", tempRecord[0], tempRecord[1], tempRecord[2], Integer.parseInt(tempRecord[3]), tempRecord[4], tempRecord[5]);
                    records[i] = output;
                    i++;

                } else {

                    printBadField(tempRecord);

                }

            }

            System.out.println("");

            for (String record : records) {

                if (record != null) {

                    System.out.println(record);

                }
            }

            scan.close();

        } catch (FileNotFoundException e) {
            System.err.println("Could not find file.");
            System.exit(-1);
        }
    }

    public static boolean fullNameIsGood(String firstName, String lastName) {

        String fullName = firstName.trim() + lastName.trim();
        if (Pattern.matches("[a-zA-Z]+", fullName.trim())) {

            return true;

        } else {

            return false;

        }

    }

    public static boolean genderIsGood(String gender) {
        if (gender.trim().equalsIgnoreCase("male") || gender.trim().equalsIgnoreCase("female")) {
            return true;
        } else {
            System.out.println("not a valid gender");
            return false;
        }

    }
    
    public static boolean emailIsGood(String email){
        boolean isChar = true;
        int i = 0;
        email = email.trim();
        
        while(isChar == true && i < email.length()) {
        
        
            isChar = isValidCharacter(email.charAt(i));
            i++;
        }
       
        
       return isChar;
    }

    public static boolean ageIsGood(String age) {
        boolean answer = false;
        age = age.trim();

        if (age.length() > 0) {

            int realAge = Integer.parseInt(age);

            if (realAge > 0 && realAge < 131) {

                answer = true;
            }

        } else {

            answer = false;

        }

        return answer;
    }

    public static boolean phoneNumberIsGood(String phoneNum) {

        String realNum = getOnlyNum(phoneNum);

        return realNum.matches("[0-9]+");

    }

    public static boolean recordIsGood(String[] record) {

        boolean answer = emailIsGood(record[5]) && fullNameIsGood(record[0], record[1]) && genderIsGood(record[2]) && ageIsGood(record[3]) && phoneNumberIsGood(record[4]);
        return answer;

    }

    public static void printBadField(String[] record) {

        if (!fullNameIsGood(record[0], record[1])) {

            System.out.println("bad Name: " + record[0].trim() + " " + record[1].trim());

        } else if (!genderIsGood(record[2])) {

            System.out.println("bad Gender: " + record[2].trim());

        } else if (!ageIsGood(record[3])) {

            System.out.println("bad Age: " + record[3].trim());

        } else if (!phoneNumberIsGood(record[4])) {

            System.out.println("bad Phone Number: " + record[4].trim());

        } else if (!emailIsGood(record[5])) {
        
            System.out.println("bad email: " + record[5].trim());
        
        }

    }

    public static String getOnlyNum(String numAsString) {
        String realVal = "";
        numAsString = numAsString.trim();

        realVal += numAsString.substring(1, 4) + numAsString.substring(5, 8) + numAsString.substring(9, 13);

        return realVal;

    }
    
    public static boolean isValidCharacter(char letter){
        
        
       return Character.isLetterOrDigit(letter) || letter == '@' || letter == '.';
        
       
        
       
    }

}
