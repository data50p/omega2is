package omega.lesson.remote;

import omega.OmegaContext;
import omega.util.SundryUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    Thread th;
    int port = 9900;

    public Server() {
        th = new Thread(this);
        th.start();
    }

    public void run() {
//log	omega.OmegaContext.sout_log.getLogger().info("ERR: " + "Server started");
        for (; ; ) {
            try {
                ServerSocket sso = new ServerSocket(port);
                for (; ; ) {
                    Socket so = sso.accept();
//log		    omega.OmegaContext.sout_log.getLogger().info("ERR: " + "lessond: Connection accepted");
                    Connection con = new Connection(so, this);
                    con.start();
                }
            } catch (IOException ex) {
                OmegaContext.sout_log.getLogger().info("ERR: " + "lessond: Exception: " + ex);
                SundryUtils.m_sleep(3000);
            }
        }
    }
}
