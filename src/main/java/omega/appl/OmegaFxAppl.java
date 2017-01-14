package omega.appl;

import fpdo.sundry.S;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import omega.Context;

public class OmegaFxAppl extends Application {

    static String[] args;

    public OmegaFxAppl() {
    }

    public static void main(String[] args) {
	OmegaFxAppl.args = args;
        launch(args);
    }

    public void start2(Stage primaryStage) {
	primaryStage.setTitle("Hello World!");
	Button btn = new Button();
	btn.setText("Say 'Hello World'");
	btn.setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		System.out.println("Hello World!");
	    }
	});

	StackPane root = new StackPane();
	root.getChildren().add(btn);
	primaryStage.setScene(new Scene(root, 300, 250));
	primaryStage.show();
    }

    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(false);

	MenuBar menuBar = new MenuBar ();
	if( System.getProperty("os.name","UNKNOWN").equals("Mac OS X")) {
	    menuBar.setUseSystemMenuBar(true);
	}

	final Menu menu1 = new Menu("File");
	final Menu menu2 = new Menu("Options");
	final Menu menu3 = new Menu("Help");

	menuBar.getMenus().addAll(menu1, menu2, menu3);

	Platform.runLater(() -> menuBar.setUseSystemMenuBar(true));


        int scW = 1000;
        int scH = 1000;
        int ww = 700;
	int hh = 330 + 22 * 3;
	int xx = (scW - ww) / 2;
        int yy = (scH - hh) / 2;

        Circle circ = new Circle(40, 40, 30);
        Group root = new Group(circ);
        Scene scene = new Scene(root, 700, 330 + 22 * 3);
        stage.setTitle("Omega2 IS");

	String aImname = Context.omegaAssets("media/default/omega_splash.gif");
	Image im = new Image("file:" + aImname);
	ImageView imView = new ImageView();
	imView.setImage(im);
	imView.setX(4);
	imView.setY(4);
        root.getChildren().addAll(imView);
	root.getChildren().add(menuBar);

        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
	System.err.println("started");
	new Thread(()->{
	    S.m_sleep(3000);
	    Platform.runLater(() -> {
	        stage.setIconified(true);
//	        stage.hide();
		new Thread(() -> {
		    S.m_sleep(500);
			omega.appl.lesson.Editor.main(args);
		}).start();
	    });
	}).start();

    }

    public void stop() {
	System.err.println("stop");
//	Platform.exit();
    }

//    public void paint(Graphics g) {
//	g.drawImage(im, 0, 0, null);
//	g.setColor(Color.yellow);
//	g.drawString(omega.Version.getVersion(), 5, 12);
//	g.drawString(omega.Version.getCWD(), 5, 322 + 20 * 0);
//	g.drawString(omega.Version.getJavaVersion(), 5, 322 + 20 * 1);
//	g.drawString(omega.Version.getXXX(), 5, 322 + 20 * 2);
//	g.drawString(omega.Version.getYYY(), 5, 322 + 20 * 3);
//    }
}
