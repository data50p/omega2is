package omega.adm.login;

import javax.swing.*;
import java.awt.*;

public class LoginAppl {
    Login l;

    LoginAppl() {
        l = LoginFactory.createLogin("Simple");
    }

    void start() {
        if (l != null) {
            omega.Context.sout_log.getLogger().info("Loaded " + l);

            JFrame f = new JFrame("LoginAppl");
            Container c = f.getContentPane();
            c.setLayout(new BorderLayout());

            l.setMode(Login.USER);
            c.add(l.getJComponent(), BorderLayout.CENTER);

            f.pack();
            f.setVisible(true);

            l.waitDone();

//log	    omega.Context.sout_log.getLogger().info("Login user name is " + l.user);
//log	    omega.Context.sout_log.getLogger().info("Login teacher name is " + l.teacher);

            f.setVisible(false);
            f.dispose();
        }
    }

    static public void main(String[] args) {
        LoginAppl la = new LoginAppl();
        la.start();
    }
}
