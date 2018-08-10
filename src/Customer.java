import java.io.Serializable;
/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class Customer implements Serializable {


    private String firstName;
    private String surname;
    private String phoneNum;
    private double credit;
    private boolean canRent;

    public Customer(){

    }

    public Customer(String fName, String sName, String phone, double credAmt, boolean can){

        firstName = fName;
        surname = sName;
        phoneNum = phone;
        credit = credAmt;
        canRent = can;

    }

    public void setName(String fName){

        firstName = fName;

    }

    public void setSurname(String sName){

        surname = sName;

    }

    public void setPhoneNum(String phone){

        phoneNum = phone;

    }

    public void setCredit(double credAmt){

        credit = credAmt;

    }

    public void setCanRent(boolean can){

        canRent = can;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public double getCredit() {
        return credit;
    }

    public boolean CanRent() {
        return canRent;
    }

    @Override
    public String toString() {
        return " ";
    }
}


