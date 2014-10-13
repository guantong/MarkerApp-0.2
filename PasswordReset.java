
/**
 * Write a description of class PasswordReset here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PasswordReset
{
    // instance variables - replace the example below with your own
    private String secretWord;
    private String password;

    /**
     * Constructor for objects of class PasswordReset
     */
    public PasswordReset(String secretWord, String password)
    {
        this.secretWord = secretWord;
        this.password = password;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public String getPassword() {
        return password;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void main (String args[]){

    }
}
