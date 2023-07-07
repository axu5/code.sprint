package util;

import java.util.Scanner;

public class UserInput {
    /**
     * The scanner object that will be used throughout the class.
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * An input method to prompt for a String from a user through the terminal.
     * Handles empty strings.
     * 
     * @param prompt The string that should be prompted to the user.
     */
    public static String input(String prompt) {
        String input = "";
        while (input.equals("")) {
            System.out.print(prompt);
            input = SCANNER.nextLine();
        }
        return input;
    }

    /**
     * Get an optional input from the user. If the user does not respond "" is
     * returned.
     * 
     * @param prompt The string that should be prompted to the user.
     */
    public static String inputOptional(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    /**
     * @param prompt                 The string that should be prompted to the user
     * @param validationErrorMessage This is the error message that should be
     *                               displayed if the validator check fails
     * @param validator              This is the validator interface that checks if
     *                               the user input is valid, it should have an
     *                               appropriate ".check(String): boolean" method
     * @return Returns the user input that is valid.
     */
    public static String input(String prompt, String validationErrorMessage, InputValidation validator) {
        while (true) {
            String input = UserInput.input(prompt);
            if (validator.check(input)) {
                try {
                    return input;
                } catch (Exception e) {
                    // If the user entered value cannot be cast to the generic <T> then an
                    // appropriate error message should be displayed
                    System.out.println(validationErrorMessage);
                    return null;
                }
            } else {
                // If the input entered isn't valid display an appropriate error message
                System.out.println(validationErrorMessage);
            }
        }
    }

    /**
     * A method to get an index of a prompt from a prompts array that is passed in.
     * 
     * @param prompt                 The question being asked
     * @param answers                The prompts or possible options that the user
     *                               can enter.
     * @param validationErrorMessage The error message that should be passed in if
     *                               the input is incorrect.
     * @return The index in the prompts array that the user selected, it is
     *         automatically converted to 0 based.
     */
    public static int inputTable(String prompt, String[] answers, String validationErrorMessage) {
        int max = answers.length;

        System.out.println(prompt);
        while (true) {
            for (int i = 0; i < answers.length; ++i) {
                System.out.printf("%d. %s\n", i + 1, answers[i]);
            }
            System.out.println();

            int indexDecimal = UserInput.getInt("Please enter your selection > ");
            if (indexDecimal >= 1 && indexDecimal <= max) {
                return indexDecimal - 1; // Subtract 1 to convert to zero based
            } else {
                System.out.printf("Please choose a number between 1 and %d\n", max);
                System.out.println(validationErrorMessage);
            }
        }
    }

    /**
     * A method to get an index of a prompt from a prompts array that is passed in.
     * 
     * @param prompt  The question being asked
     * @param answers The prompts or possible options that the user
     *                can enter.
     * @return The index in the prompts array that the user selected, it is
     *         automatically converted to 0 based.
     */
    public static int inputTable(String prompt, String[] answers) {
        return UserInput.inputTable(prompt, answers, "");
    }

    /**
     * Simple method to get an integer from the user input.
     * 
     * @param prompt The prompt that should be displayed to the user through the
     *               terminal.
     * @return Returns the integer that was entered as a string to the terminal
     */
    public static int getInt(String prompt) {
        String errorString = "Please enter a valid integer.";
        while (true) {
            String input = UserInput.input(prompt);
            try {
                // Try to return a parsed integer.
                return Integer.parseInt(input);
            } catch (Exception _exception) {
                // If an error occurs, ask again with an error message.
                System.out.println(errorString);
            }
        }
    }

    /**
     * Simple method to get an integer from the user input.
     * 
     * @param prompt The prompt that should be displayed to the user through the
     *               terminal.
     * @param min    The minimum integer the value can be (inclusive)
     * @param max    The maximum integer the value can be (exclusive)
     * @return Returns the integer that was entered as a string to the terminal
     */
    public static int getInt(String prompt, int min, int max) {
        String errorString = "Please enter a valid integer. It has to be between " + min + " and " + (max - 1);
        while (true) {
            int integer = UserInput.getInt(prompt);
            if (integer < min || integer >= max) {
                System.out.println(errorString);
                continue;
            } else {
                return integer;
            }
        }
    }

    /**
     * Simple method to get a double from the user input.
     * 
     * @param prompt The prompt that should be displayed to the user.
     * @return Returns the double that was entered as string to the terminal.
     */
    public static double getDouble(String prompt) {
        String errorMessage = "Please enter a valid decimal number.";
        while (true) {
            String input = UserInput.input(prompt);
            try {
                // Try to return the parsed floating point number.
                return Double.parseDouble(input);
            } catch (Exception _exception) {
                // If an error occurs, ask again with an error message.
                System.out.println(errorMessage);
            }
        }
    }

    /**
     * @param prompt  The prompt that will be shown to the user.
     * @param options The possible values that may be inputted.
     * @return The options value that the user input.
     */
    public static String useOptionsIgnoreCase(String prompt, String[] options) {
        String errorMessage = "The valid options are:\n";
        for (String opt : options) {
            errorMessage += "- " + opt + "\n";
        }

        boolean valid = false;
        while (!valid) {
            System.out.println(errorMessage);
            String input = UserInput.input(prompt);
            for (String opt : options) {
                if (input.equalsIgnoreCase(opt)) {
                    return opt;
                }
            }
            System.out.println("\nThat is not a valid input.");
        }

        return "";
    }
}
