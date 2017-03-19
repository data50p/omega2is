package omega.lesson.remote;

import fpdo.sundry.S;
import omega.OmegaConfig;
import omega.OmegaContext;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Connection extends Thread {
    Socket so;
    Server server;

    Connection(Socket so, Server server) {
        super("lessond.Connection");
        this.server = server;
        this.so = so;
    }

    void serve(InputStream is, DataOutputStream dos) { // BufferedReader rd, DataOutputStream dos) {
        try {
            LOOP:
            for (; ; ) {
                int ch = is.read(); //.readLine();
//log		omega.OmegaContext.sout_log.getLogger().info("ERR: " + "read lessond " + ch);
                if (ch == -1)
                    break;

                try {
                    switch (ch) {
                        case 'l':
                            omega.lesson.Lesson.le_canvas.gotoBoxRel(1, 0);
                            break;
                        case 'h':
                            omega.lesson.Lesson.le_canvas.gotoBoxRel(-1, 0);
                            break;
                        case 'k':
                            omega.lesson.Lesson.le_canvas.gotoBoxRel(0, -1);
                            break;
                        case 'j':
                            omega.lesson.Lesson.le_canvas.gotoBoxRel(0, 1);
                            break;
                        case ' ':
                            omega.lesson.Lesson.le_canvas.selectBox(false, S.ct());
                            break;
                        case 'Q':
                            break LOOP;
                    }
                } catch (NullPointerException ex) {
                }
            }
        } catch (IOException ex) {
            OmegaContext.sout_log.getLogger().info("ERR: " + "serve(): Exception " + ex);
        }
    }

    public void run() {
        long ct0 = S.ct();

        if (OmegaConfig.T) OmegaContext.sout_log.getLogger().info("ERR: " + "lessond Connection established");
        try {
                /*BufferedReader rd = new BufferedReader(new InputStreamReader(*/
            InputStream is = so.getInputStream(); // ));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(so.getOutputStream()));

            serve(is, dos);
            is.close();
            dos.close();
            so.close();
        } catch (IOException ex) {
        }
    }
}
