package util;

public abstract interface InputValidation {
    /**
     * @param input this is the input the check method should validate
     * @return boolean this is the boolean that should be returned, true if valid,
     *         false if not.
     */
    public boolean check(String input);
}