package omega.lesson;

import omega.lesson.canvas.LessonCanvas;
import omega.lesson.machine.Target;

public class LessonContext {
    Lesson le;

    LessonContext(Lesson le) {
	this.le = le;
    }

    public Lesson getLesson() {
	return le;
    }

    public LessonCanvas getLessonCanvas() {
	return le.le_canvas;
    }

    public Target getTarget() {
	return le.machine.getTarget();
    }
}
