package omega.util;

import java.util.logging.Logger;

/**
 * @author lars
 */
public class LoggerFactory {
    public static Logger getLogger(Class type) {
        return Logger.getLogger(type.getSimpleName());
    }
}
