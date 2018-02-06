package webAppProjetLivre.classesTravail;

import webAppProjetLivre.beans.User;

public class UserDao {
    public boolean checkLogin(User user) {
        return user.getUsername().equals("admin") && user.getPassword().equals("nimda");
    }
}
