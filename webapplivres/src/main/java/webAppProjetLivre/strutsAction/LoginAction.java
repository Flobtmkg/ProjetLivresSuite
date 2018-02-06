package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import webAppProjetLivre.beans.User;
import webAppProjetLivre.classesTravail.UserDao;

public class LoginAction extends ActionSupport {
    private UserDao userDAO;
    private User user;

    public void setUserDAO(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String execute() {
        if (this.userDAO.checkLogin(this.user)) {
            return SUCCESS;
        }
        return ERROR;
    }
}
