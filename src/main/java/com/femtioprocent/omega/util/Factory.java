package com.femtioprocent.omega.util;

import com.femtioprocent.omega.OmegaContext;

public class Factory {
    static public Object createObject(String clazz_name) {
        try {
            Class clazz = Class.forName(clazz_name);
            Object o = clazz.newInstance();
            return o;
        } catch (ClassNotFoundException ex) {
            OmegaContext.sout_log.getLogger().info("ERR: " + "Can't load class " + clazz_name + ": " + ex);
        } catch (IllegalAccessException ex) {
            OmegaContext.sout_log.getLogger().info("ERR: " + "Can't access class " + clazz_name + ": " + ex);
        } catch (InstantiationException ex) {
            OmegaContext.sout_log.getLogger().info("ERR: " + "Can't instantiate class " + clazz_name + ": " + ex);
        }
        return null;
    }
}
