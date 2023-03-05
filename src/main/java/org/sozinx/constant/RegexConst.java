package org.sozinx.constant;

/**
 * A class with a list of regex.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */
public class RegexConst {
    /**
     * <b>Email regex</b><br>
     * Two parts of email could contain english and ukrainian letters, numbers and only first part could contain special characters.<br>
     */
    public static final String EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-А-ЩЬЮЯҐЄІЇа-щьюяґєії]+@[a-zA-Z0-9.-А-ЩЬЮЯҐЄІЇа-щьюяґєії]+$";
    /**
     * <b>Password regex</b><br>
     * Password must contain at least one digit [0-9].<br>
     * Password must contain at least one lowercase Latin character [a-z] or Cyrillic character [а-щьюяґєії].<br>
     * Password must contain at least one uppercase Latin character [A-Z] or Cyrillic character [А-ЩЬЮЯҐЄІЇ].<br>
     * Password must contain at least one special character like ! @ # & ( ).<br>
     * Password must contain a length of at least 8 characters and a maximum of 20 characters.<br>
     */
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-zа-щьюяґєії])(?=.*[A-ZА-ЩЬЮЯҐЄІЇ])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,25}$";
    /**
     * <b>Time regex</b><br>
     * Time must contain only natural numbers.<br>
     */
    public static final String TIME = "[0-9]+";
}
