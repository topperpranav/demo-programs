package runtimeCalculator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Used to calculate the counts
 *
 * @author Pranav
 * @since 2019-06-19
 */
public class Main {

    static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss,SSS";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        String dateFormat = null;
        System.out.println("************************ README ******************************");
        System.out.println("This application will calculate the average time taken between two unique substrings,\n"
                + "if time is present in the same line as the string. \nIn the next few steps you would be asked the date format, file path and substrings."
                + "\nWhile entering substring, make sure you enter the start substring first and then the end substring."
                + "\ni.e the substring entered later must be in a future time with reference to the first substring."
                + "\nCurrently this application can be used to search time difference multiple start and end string pairs inside a file."
                + "\nPlease ensure that there are no string escape characters in any of the query string");
        System.out.println("**************************************************************\n\n");

        System.out.println("Do you want to use a custom date format? If yes then enter the date format else enter 'n'."
                + "\nIf you donot enter any date format then the default date format '" + DEFAULT_DATE_FORMAT + "' will be used.");
        String response = sc.nextLine();
        if (response == null || response.isEmpty() || response.equalsIgnoreCase("n")) {
            dateFormat = DEFAULT_DATE_FORMAT;
        } else {
            dateFormat = response;
        }
        System.out.println("Enter file path:");
        String filePath = sc.nextLine();

        if (!checkFileIntegrity(filePath)) {
            System.exit(1);
        }
        //Integrity of file is check before, so it is safe to read unless opening the file poses a problem
        List<String> fileContents = readFileInList(filePath);

        // Get the string pairs to search from the user        
        List<List<String>> startEndStrings = getStringsToSearch();

        System.out.println("\n \n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ RESULTS $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        //For each pair, print the average counts
        for (List<String> strs : startEndStrings) {
            getTimeDifference(fileContents, strs.get(0), strs.get(1), dateFormat);
        }
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    /**
     * Gets the difference between the start string and the end string
     *
     * @param fileContents
     * @param beforeStr: The first string
     * @param afterStr: The second string
     * @throws ParseException
     */
    public static void getTimeDifference(List<String> fileContents, String beforeStr, String afterStr, String dateFormat) {
        //Sanity checks
        if (beforeStr == null || afterStr == null || beforeStr.isEmpty() || afterStr.isEmpty()) {
            System.out.println("First or last string is empty. Skipping check for this");
            return;
        }

        if (beforeStr.equals(afterStr)) {
            System.out.println("Both strings cannot be same. Enter the strings again. Please");
            return;
        }

        List<Long> differences = new ArrayList<>();
        Date initialDate = null;
        Date laterDate = null;
        for (String line : fileContents) {
            if (line.contains(beforeStr)) {
                laterDate = null;
                initialDate = getDate(line.split("  ")[0], dateFormat);
                // System.out.println("Initial date is: " + initialDate);
            }

            if (line.contains(afterStr) && initialDate != null) {
                laterDate = getDate(line.split("  ")[0], dateFormat);
                differences.add(laterDate.getTime() - initialDate.getTime());
                //System.out.println("End date is: " + laterDate);
                initialDate = null;
            }
        }
        int i = 1;
        int sum = 0;
        for (long l : differences) {
            //System.out.println(" Difference-" + i++ + " : " + l);
            sum += l;
        }
        float avg;
        //Since size can be zero, then average cannot be calculated. 
        if (differences.size() > 0) {
            avg = sum / differences.size();
        } else {
            avg = sum / 1;
        }
        if (avg < 1000) {
            System.out.println("Average : " + avg + " ms between string: " + beforeStr + " and string: " + afterStr + " after processing " + differences.size() + " instances");
        } else {
            System.out.println("Average : " + (avg / 1000) + " seconds between string: " + beforeStr + " and string: " + afterStr + " after processing " + differences.size() + " instances");
        }

    }

    /**
     * Gets the date in a predefined format
     *
     * @param date
     * @param format
     * @return
     */
    public static Date getDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException ex) {
            System.out.println("Unparceaple date: '" + dateStr + "' with format: '" + format + "' ");
            System.exit(1);
        }
        return date;
    }

    /**
     * Reads the file contents to a List of string where each item in the list
     * refers to a line in the file.
     *
     * @param fileName
     * @return
     */
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Problem in opening file: " + e.getStackTrace());
            System.exit(1);
        }
        return lines;
    }

    /**
     * Checks whether the file is in good shape or not.
     *
     * @param filePath
     * @return
     */
    public static boolean checkFileIntegrity(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Valid file path not entered. I am hurt. Bye.");
            return false;
        }
        File f = new File(filePath);
        return checkFileIntegrity(f);
    }

    /**
     * Checks whether a file is valid or not
     *
     * @param file
     * @return
     */
    public static boolean checkFileIntegrity(File f) {
        if (!f.isFile()) {
            System.out.println("Path entered is not a valid file. I am hurt. Bye.");
            return false;
        }
        if (f.length() == 0) {
            System.out.println("File is empty. Exiting. See you in the next run");
            return false;
        }
        return true;
    }

    /**
     * Gets a list of list of strings where each individual list contains
     * exactly 2 elements. First listItem is the start string and the second
     * list item is the end string.
     *
     * @return
     */
    public static List<List<String>> getStringsToSearch() {
        List<List<String>> startEndSubStrings = new ArrayList<>();
        while (true) {
            List<String> startEndSubstring = new ArrayList<>();
            System.out.println("Enter first string:");
            startEndSubstring.add(sc.nextLine());

            System.out.println("Enter second string:");
            startEndSubstring.add(sc.nextLine());
            startEndSubStrings.add(startEndSubstring);
            if (startEndSubstring.get(0) == null || startEndSubstring.get(0).isEmpty() || startEndSubstring.get(1) == null || startEndSubstring.get(1).isEmpty()) {
                System.out.println("Strings cannot be empty");
                continue;
            }

            if (startEndSubstring.get(0).equals(startEndSubstring.get(1))) {
                System.out.println("Both strings cannot be same. Enter the strings again. Please");
                continue;
            }

            System.out.println("Want to add another pair of strings to search? Enter Y for yes and N for no");
            boolean addMore = (sc.nextLine().equalsIgnoreCase("Y")) ? true : false;
            if (!addMore) {
                break;
            }
        }
        return startEndSubStrings;
    }
}
