package org.sozinx.constant;

public class RegexConst {
    public static final String EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-А-ЩЬЮЯҐЄІЇа-щьюяґєії]+@[a-zA-Z0-9.-А-ЩЬЮЯҐЄІЇа-щьюяґєії]+$";
    /*
        Password must contain at least one digit [0-9].
        Password must contain at least one lowercase Latin character [a-z] or Cyrillic character [а-щьюяґєії].
        Password must contain at least one uppercase Latin character [A-Z] or Cyrillic character [А-ЩЬЮЯҐЄІЇ].
        Password must contain at least one special character like ! @ # & ( ).
        Password must contain a length of at least 8 characters and a maximum of 20 characters.
     */
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-zа-щьюяґєії])(?=.*[A-ZА-ЩЬЮЯҐЄІЇ])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,25}$";
    public static final String TIME = "[0-9]+";
}
