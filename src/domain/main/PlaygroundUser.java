package main;

import main.user.Email;
import main.user.User;

public class PlaygroundUser
{
    public static void main(String[] args)
    {
//        Email email = new Email("test@test.at");
//        User user = new User(email);
//        System.out.println(user.toString());
//        System.out.println(email.geteMail());
        Email email = new Email("test@test.at");
        User user = new User(email);
        System.out.println(email.geteMail());
    }
}
