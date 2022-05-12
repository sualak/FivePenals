package main.user;

import main.user.User;

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

    public void sendRequest(User sender, User receiver){

        incomingRequests.add(receiver);
        outGoingRequest.add(sender);

    }
}
