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
    //added owner (is sender)
    private final User owner;

    public Social(User owner)
    {
        //todo ensurer muss geschrieben werden
        this.owner = owner;
        //this.owner =Ensure.ensureOwnerNotNull(owner,"user");
    }


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

    public void sendRequest(User receiver)
    {
        Ensure.ensureUserNotInContacts(owner,receiver,contacts);
        outGoingRequest.add(receiver);
        receiver.getsData().addUserToIncomingRequests(owner);
        crossAdd(receiver);
    }

    private void addUserToIncomingRequests(User user){
        incomingRequests.add(user);
    }

    public User showRequests(){
        for (User request: incomingRequests) {
            return request;
        }
        return null;
    }
    // no need to clear from sets, to prevent request-spams.
    public boolean handleRequest(User friendRequest, boolean confirm){
        Ensure.ensureContactIsRequesting(friendRequest,incomingRequests);
        //todo request not handled jet, maybe with showRequests() ?
        if(confirm) {
            contacts.add(friendRequest);
            friendRequest.getsData().contacts.add(owner);
            friendRequest.getsData().outGoingRequest.remove(owner);
            incomingRequests.remove(friendRequest);
        }
        else{
            incomingRequests.remove(friendRequest);
            friendRequest.getsData().outGoingRequest.remove(owner);
        }
        return confirm;
    }

    private void addWhenOutgoingIsAccapted(User user)
    {
        if (user.getsData().handleRequest(owner, true)) {
            contacts.add(user);
            user.getsData().contacts.add(owner);
            incomingRequests.remove(user);
            user.getsData().outGoingRequest.remove(owner);
        }
        else {
            user.getsData().incomingRequests.remove(owner);
            outGoingRequest.remove(user);
        }
    }

    private void crossAdd(User receiver){
        if(incomingRequests.contains(receiver) && receiver.getsData().incomingRequests.contains(owner))
            contacts.add(receiver);
            receiver.getsData().contacts.add(owner);
            outGoingRequest.remove(receiver);
            receiver.getsData().incomingRequests.remove(owner);
    }

    public void removeContact(User contact){
        contacts.remove(contact);
        if(contact.getsData().contacts.remove(owner))
            contacts.remove(contact);

    }

    public void printContacts(){
        System.out.println(contacts);
    }

}

