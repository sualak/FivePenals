package main.user;

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
        receiver.getSocial().addUserToIncomingRequests(owner);
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
            friendRequest.getSocial().contacts.add(owner);
            friendRequest.getSocial().outGoingRequest.remove(owner);
            incomingRequests.remove(friendRequest);
        }
        else{
            incomingRequests.remove(friendRequest);
            friendRequest.getSocial().outGoingRequest.remove(owner);
        }
        return confirm;
    }
    //todo als elsif in handleRequest. alles andre macht keinen sinn du heisl!!!!!!!
    private void addWhenOutgoingIsAccapted(User user)
    {
        if (user.getSocial().handleRequest(owner, true)) {
            contacts.add(user);
            user.getSocial().contacts.add(owner);
            incomingRequests.remove(user);
            user.getSocial().outGoingRequest.remove(owner);
        }
        else {
            user.getSocial().incomingRequests.remove(owner);
            outGoingRequest.remove(user);
        }
    }

    private void crossAdd(User receiver){
        if(incomingRequests.contains(receiver) && receiver.getSocial().incomingRequests.contains(owner))
            contacts.add(receiver);
            receiver.getSocial().contacts.add(owner);
            outGoingRequest.remove(receiver);
            receiver.getSocial().incomingRequests.remove(owner);
    }

    public void removeContact(User contact){
        contacts.remove(contact);
        if(contact.getSocial().contacts.remove(owner))
            contacts.remove(contact);

    }

    public void printContacts(){
        System.out.println(contacts);
    }

}

