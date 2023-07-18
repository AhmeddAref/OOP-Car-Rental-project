package BackendCode;

import java.io.Serializable;

/**
 *
 * @author @YoussefHesham
 */
public abstract class Person implements Serializable {

    protected int ID;
    protected String SSN, Name, Contact_No;

    public Person() {
    }
    public Person(int ID, String SSN, String Name, String Contact_No) {
        this.ID = ID;
        this.SSN = SSN;
        this.Name = Name;
        this.Contact_No = Contact_No;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getSSN() {
        return SSN;
    }
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getContact_No() {
        return Contact_No;
    }
    public void setContact_No(String Contact_No) {
        this.Contact_No = Contact_No;
    }
    public abstract void Add();
    public abstract void Update();
    public abstract void Remove();
    @Override
    public String toString() {
        return "Person_new{" + "ID=" + ID + ", SSN=" + SSN + ", Name=" + Name + ", Contact_No=" + Contact_No + '}';
    }
    /**
     * A valid SSN consists of 9 characters, only digits.
     * @param SSN, The SSN whose validity is to be checked
     * @return true if the passed SSN is valid 
     */
    public static boolean isSSNValid(String SSN) {
        boolean flag = true;
        if (SSN.length() == 9) {
            for (int i = 0; i < SSN.length(); i++) {
                if (!Character.isDigit(SSN.charAt(i))) {
                    flag = false;
                    break;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }
    /**
     * A valid Contact No. has 11 digits and starts with "01"
     * @param contact 
     * @return true if the contact is valid 
     */
    public static boolean isContactNoValid(String contact) {
        boolean flag = true;
        if (contact.length() == 11) {
            if (contact.substring(0, 2).equals("01")) {
                for (int i = 0; i < contact.length(); i++) {
                    if (!Character.isDigit(contact.charAt(i))) {
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }
    /**
     * A valid name can contain only letters and white spaces
     * @param Name
     * @return true if the name is valid
     */
    public static boolean isNameValid(String Name) {
        boolean flag = false;
        for (int i = 0; i < Name.length(); i++) {
//            Name can contain white spaces
            if (Character.isLetter(Name.charAt(i)) | Name.charAt(i) == ' ') {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }
    /**
     * A valid ID can only be digit greater than 0
     * @param ID
     * @return true if the ID is valid
     */
    public static boolean isIDvalid(String ID) {
        boolean flag = true;
        for (int i = 0; i < ID.length(); i++) {
            if (!Character.isDigit(ID.charAt(i))) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (Integer.parseInt(ID) <= 0) {
                flag = false;
            }
        }
        return flag;
    }
}