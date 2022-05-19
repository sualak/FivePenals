package main.user;

import main.user.User;
import validation.Ensure;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Social
{
    private final Set<User> contacts = new HashSet<>();
    private final Set<User> incomingRequests = new HashSet<>();
    private final Set<User> outGoingRequest = new HashSet<>();


    public Set<User> getContacts()
    {
        return Collections.unmodifiableSet(contacts);
    }

    public Set<User> getIncomingRequests()
    {
        return Collections.unmodifiableSet(incomingRequests);
    }

    public Set<User> getOutGoingRequest()
    {
        return Collections.unmodifiableSet(outGoingRequest);
    }

    public void sendRequest(User sender, User receiver)
    {
        Ensure.ensureUserNotInContacts(sender,receiver,contacts);
        outGoingRequest.add(sender);
        incomingRequests.add(receiver);
        crossAdd(receiver);

    }
    public User showRequests(){
        for (User request: incomingRequests) {
            return request;
        }
        return null;
    }
    // no need to clear from sets, to prevent request-spams.
    /*public void handleRequest(User sender, boolean confirm){
        if (sender.equals(showRequests()))
            handleRequest(sender, confirm);
        else throw new IllegalArgumentException("this user didnt send a request");
    }*/

    private void crossAdd(User receiver){
        if(incomingRequests.contains(receiver))
            contacts.add(receiver);
    }

    public void removeContact(User contact){
        Ensure.ensureNotNullNotBlank(contact);

        contacts.remove(contact);
    }

    public void printContacts(){
        System.out.println(contacts);
    }

}

