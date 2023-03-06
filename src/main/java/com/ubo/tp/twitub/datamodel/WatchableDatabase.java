package main.java.com.ubo.tp.twitub.datamodel;


import main.java.com.ubo.tp.twitub.core.EntityManager;

import javax.xml.crypto.Data;

public class WatchableDatabase implements IDatabaseObserver {

  protected static final int POLLING_TIME = 1000;

  protected Thread mWatcherThread;

  protected final Database mDatabase;

  protected Database oldDatabase = new Database();

  public WatchableDatabase(Database mDatabase) {
    super();
    this.mDatabase = mDatabase;
  }

  public void initWatching() {
    if (mDatabase != null) {
      this.startPolling();
    } else {
      System.err.println("Impossible de surveiller une base de données nulle.");
    }
  }

  @Override
  public void addObserver(EntityManager mEntityManager) {

  }

  private void startPolling() {
    mWatcherThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          // Attente avant la prochaine vérification
          Thread.sleep(POLLING_TIME);

          // Vérification des changements
          watchDatabase();

          // Relancement automatique
          startPolling();
        } catch (InterruptedException e) {
          System.err.println("Surveillance du répertoire interrompue.");
        }
      }
    });

    mWatcherThread.start();
  }

  /**
   * Lancement d'une étape de surveillance (avec notification des changements
   * aux observeurs).
   */
  protected void watchDatabase() {
    if (mDatabase.mUsers.size() != 0 || mDatabase.mTwits.size() != 0) {
      Database presentDatabase = mDatabase;

      for (User user : presentDatabase.mUsers) {
        if (!oldDatabase.mUsers.contains(user)) {
          notifyUserAdded(user);
          oldDatabase.mUsers.add(user);
        }
      }

      for (User user : oldDatabase.mUsers) {
        if (!presentDatabase.mUsers.contains(user)) {
          notifyUserDeleted(user);
          oldDatabase.mUsers.remove(user);
        }
      }

      for (Twit twit : presentDatabase.mTwits) {
        if (!oldDatabase.mTwits.contains(twit)) {
          notifyTwitAdded(twit);
          oldDatabase.mTwits.add(twit);
        }
      }

      for (Twit twit : oldDatabase.mTwits) {
        if (!presentDatabase.mTwits.contains(twit)) {
          notifyTwitDeleted(twit);
          oldDatabase.mTwits.remove(twit);
        }
      }
    }
  }

  @Override
  public void notifyTwitAdded(Twit addedTwit) {
    System.out.println("Twit " + addedTwit + "added");
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit) {
    System.out.println("Twit " + deletedTwit + "deleted");
  }

  @Override
  public void notifyTwitModified(Twit modifiedTwit) {
    System.out.println("Twit " + modifiedTwit + "modified");
  }

  @Override
  public void notifyUserAdded(User addedUser) {
    System.out.println("User " + addedUser + "added");
  }

  @Override
  public void notifyUserDeleted(User deletedUser) {
    System.out.println("User " + deletedUser + "deleted");
  }

  @Override
  public void notifyUserModified(User modifiedUser) {
    System.out.println("User " + modifiedUser + "modified");
  }
}
