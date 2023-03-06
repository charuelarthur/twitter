package main.java.com.ubo.tp.twitub.controller;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.User;

import java.rmi.server.UID;
import java.util.HashSet;
import java.util.UUID;

public class UserController {
  private EntityManager mEntityManager;
  private Database mDatabase;

  public UserController(EntityManager mEntityManager, Database mDatabase) {
    this.mEntityManager = mEntityManager;
    this.mDatabase = mDatabase;
  }
  public boolean createUser(String name, String password, String tag, String avatar) {
    if(checkUserTag(tag)) {
      return false;
    }
    //GENERATE ID
    UUID uid = UUID.randomUUID();
    User user = new User(uid, tag, password, name, new HashSet<>(), avatar);
    this.mDatabase.addUser(user);
    this.mEntityManager.sendUser(user);
    return true;
  }

  private boolean checkUserTag(String tag) {
    //check in database if tag already exist
    for (User user : mDatabase.getUsers()) {
      if (user.getUserTag().equals(tag)) {
        return true;
      }
    }
    return false;
  }
}
