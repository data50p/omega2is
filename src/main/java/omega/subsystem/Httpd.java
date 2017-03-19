package omega.subsystem;

import omega.servers.httpd.Server;

public class Httpd extends Subsystem {
    public static Server httpd;

    public Httpd() {
    }

    public void init(Object arg) {
        if (httpd == null) {
            httpd = new Server(8089);
            httpd.start();
        }
    }
}
