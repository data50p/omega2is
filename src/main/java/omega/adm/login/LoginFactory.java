package omega.adm.login;

import omega.OmegaContext;

public class LoginFactory {
    static public Login createLogin(String name) {
        String n = "omega.adm.login.Login" + name;
        try {
            Login login = (Login) omega.util.Factory.createObject(n);
            return login;
        } catch (Exception ex) {
            OmegaContext.sout_log.getLogger().info("ERR: " + "Can't create " + n + ": " + ex);
        }
        return null;
    }
}
