package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class DatabaseConsoleObserver implements IDatabaseObserver {

    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        System.out.println("Tweet créé " + addedTwit.getText());
    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {
        System.out.println("Tweet delete " + deletedTwit.getText());
    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {
        System.out.println("Tweet modified " + modifiedTwit.getText());
    }

    @Override
    public void notifyUserAdded(User addedUser) {
        System.out.println("User add " + addedUser.getName());
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        System.out.println("User delete " + deletedUser.getName());
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        System.out.println("User modified " + modifiedUser.getName());
    }
}
