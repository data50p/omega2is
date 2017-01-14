package omega.lesson.appl;

import omega.lesson.Lesson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class LessonRuntime extends ApplLesson {

    public LessonRuntime(String title, String fn, boolean with_frame, char run_mode) {
	super(title, false);

	Window wi;
	JFrame f = this;
	wi = f;

	ApplContext.top_frame = this;

	f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent ev) {
		//System.exit(0);
	    }
	});

	Lesson le = new Lesson(run_mode);
	le.runLessons(wi,
		(JPanel) (f.getContentPane()),
		fn,
		false,
		with_frame);
    }
}
