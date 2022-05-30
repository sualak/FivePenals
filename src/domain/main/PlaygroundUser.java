package main;

import main.dataBase.DataBaseGIdentifiers;
import main.dataBase.DataBasePIdentifiers;
import main.user.Email;
import main.user.Password;
import main.user.Personal;
import main.user.User;

import java.io.IOException;
import java.time.LocalDate;

public class PlaygroundUser
{
    public static void main(String[] args) throws IOException
    {
//        Email email = new Email("test@test.at");
//        User user = new User(email);
//        System.out.println(user.toString());
//        System.out.println(email.geteMail());
        Email email = new Email("test@test.at");
        Password password = new Password("Testpasswort1");
//        User user = new User(email, password);
//        System.out.println(email.geteMail());
//        System.out.println(password.getPassword());
        Personal test = new Personal(Personal.Titel.TEST2, "Test", "Test", LocalDate.of(1990,1,1));
//        System.out.println(test.getvName());
        User user = new User(email, password, test);
//        DataBaseGIdentifiers dataBaseGIdentifiers = new DataBaseGIdentifiers();
//        user.removeKeyword(1,dataBaseGIdentifiers);
//        System.out.println(user.getPassword().getPassword());
//        user.resetPassword("Testt2");
//        System.out.println(user.getPassword().getPassword());
    }
}
