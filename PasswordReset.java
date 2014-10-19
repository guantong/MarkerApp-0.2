
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

    public static void main (String args[]){

    }
}
