/*
 * Copyright 1999,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lotfi.batchFile.helper;

import java.net.URL;

import java.util.Properties;


public class OptionConverter {
    static String DELIM_START = "${";
    static char DELIM_STOP = '}';
    static int DELIM_START_LEN = 2;
    static int DELIM_STOP_LEN = 1;


    public static String[] concatanateArrays(String[] l, String[] r) {
        int len = l.length + r.length;
        String[] a = new String[len];

        System.arraycopy(l, 0, a, 0, l.length);
        System.arraycopy(r, 0, a, l.length, r.length);

        return a;
    }

    public static String convertSpecialChars(String s) {
        char c;
        int len = s.length();
        StringBuffer sbuf = new StringBuffer(len);

        int i = 0;

        while (i < len) {
            c = s.charAt(i++);

            if (c == '\\') {
                c = s.charAt(i++);

                if (c == 'n') {
                    c = '\n';
                } else if (c == 'r') {
                    c = '\r';
                } else if (c == 't') {
                    c = '\t';
                } else if (c == 'f') {
                    c = '\f';
                } else if (c == '\b') {
                    c = '\b';
                } else if (c == '\"') {
                    c = '\"';
                } else if (c == '\'') {
                    c = '\'';
                } else if (c == '\\') {
                    c = '\\';
                }
            }

            sbuf.append(c);
        }

        return sbuf.toString();
    }

    public static String getSystemProperty(String key, String def) {
        try {
            return System.getProperty(key, def);
        } catch (Throwable e) { // MS-Java throws com.ms.security.SecurityExceptionEx
            return def;
        }
    }

    public static Object instantiateByKey(
            Properties props, String key, Class superClass, Object defaultValue) {
        String className = findAndSubst(key, props);

        if (className == null) {
//      getLogger().error("Could not find value for key {}", key);

            return defaultValue;
        }

        // Trim className to avoid trailing spaces that cause problems.
        return instantiateByClassName(className.trim());
    }


    public static boolean toBoolean(String value, boolean dEfault) {
        if (value == null) {
            return dEfault;
        }

        String trimmedVal = value.trim();

        if ("true".equalsIgnoreCase(trimmedVal)) {
            return true;
        }

        if ("false".equalsIgnoreCase(trimmedVal)) {
            return false;
        }

        return dEfault;
    }

    public static int toInt(String value, int dEfault) {
        if (value != null) {
            String s = value.trim();

            try {
                return Integer.valueOf(s).intValue();
            } catch (NumberFormatException e) {
//        getLogger().error("[{}] is not in proper int form.", s);
            }
        }

        return dEfault;
    }

//  public static Level toLevel(String value, Level defaultValue) {
//    if (value == null) {
//      return defaultValue;
//    }
//
//    value = value.trim();
//    int hashIndex = value.indexOf('#');
//
//    if (hashIndex == -1) {
//      if ("NULL".equalsIgnoreCase(value)) {
//        return null;
//      } else {
//        // no class name specified : use standard Level class
//        return (Level) Level.toLevel(value, defaultValue);
//      }
//    }
//
//    Level result = defaultValue;
//
//    String clazz = value.substring(hashIndex + 1);
//    String levelName = value.substring(0, hashIndex);
//
//    // This is degenerate case but you never know.
//    if ("NULL".equalsIgnoreCase(levelName)) {
//      return null;
//    }
//
//    try {
//      Class customLevel = Loader.loadClass(clazz);
//
//      // get a ref to the specified class' static method
//      // toLevel(String, org.apache.log4j.Level)
//      Class[] paramTypes =
//        new Class[] { String.class, org.apache.log4j.Level.class };
//      java.lang.reflect.Method toLevelMethod =
//        customLevel.getMethod("toLevel", paramTypes);
//
//      // now call the toLevel method, passing level string + default
//      Object[] params = new Object[] { levelName, defaultValue };
//      Object o = toLevelMethod.invoke(null, params);
//
//      result = (Level) o;
//    } catch (ClassNotFoundException e) {
//      getLogger().warn("custom level class [" + clazz + "] not found.");
//    } catch (NoSuchMethodException e) {
//      getLogger().warn(
//        "custom level class [" + clazz + "]"
//        + " does not have a class function toLevel(String, Level)", e);
//    } catch (java.lang.reflect.InvocationTargetException e) {
//      getLogger().warn(
//        "custom level class [" + clazz + "]" + " could not be instantiated", e);
//    } catch (ClassCastException e) {
//      getLogger().warn(
//        "class [" + clazz + "] is not a subclass of org.apache.log4j.Level", e);
//    } catch (IllegalAccessException e) {
//      getLogger().warn(
//        "class [" + clazz
//        + "] cannot be instantiated due to access restrictions", e);
//    } catch (Exception e) {
//      getLogger().warn(
//        "class [" + clazz + "], level [" + levelName + "] conversion failed.",
//        e);
//    }
//
//    return result;
//  }

    public static long toFileSize(String value, long dEfault) {
        if (value == null) {
            return dEfault;
        }

        String s = value.trim().toUpperCase();
        long multiplier = 1;
        int index;

        if ((index = s.indexOf("KB")) != -1) {
            multiplier = 1024;
            s = s.substring(0, index);
        } else if ((index = s.indexOf("MB")) != -1) {
            multiplier = 1024 * 1024;
            s = s.substring(0, index);
        } else if ((index = s.indexOf("GB")) != -1) {
            multiplier = 1024 * 1024 * 1024;
            s = s.substring(0, index);
        }

        if (s != null) {
            try {
                return Long.valueOf(s).longValue() * multiplier;
            } catch (NumberFormatException e) {
//        getLogger().error("[{}] is not in proper int form.", s);
//        getLogger().error("[" + value + "] not in expected format.", e);
            }
        }

        return dEfault;
    }

    public static String findAndSubst(String key, Properties props) {
        String value = props.getProperty(key);

        if (value == null) {
            return null;
        }

        try {
            return substVars(value, props);
        } catch (IllegalArgumentException e) {
//      getLogger().error("Bad option value [" + value + "].", e);

            return value;
        }
    }


    public static Object instantiateByClassName(String className) {
        if (className != null) {
            try {
                Class classObj = Loader.loadClass(className);
//
//        if (!superClass.isAssignableFrom(classObj)) {
//          getLogger().error(
//            "A \"" + className + "\" object is not assignable to a \""
//            + superClass.getName() + "\" variable.");
//          getLogger().error(
//            "The class \"" + superClass.getName() + "\" was loaded by ");
//          getLogger().error(
//            "[" + superClass.getClassLoader() + "] whereas object of type ");
//          getLogger().error(
//            "\"" + classObj.getName() + "\" was loaded by ["
//            + classObj.getClassLoader() + "].");

//          return defaultValue;
//        }

                //System.out.println("About to call classObj.newInstance(), "+classObj.getName());

                return classObj.newInstance();
            } catch (NoClassDefFoundError ncfe) {
//        getLogger().error("Could not instantiate object of class [" + className + "].", ncfe);
            } catch (Throwable e) {
//        getLogger().error("Could not instantiate object of class [" + className + "].", e);
            }
        }

        return null;
    }

    public static String substVars(String val, Properties props) {

        StringBuffer sbuf = new StringBuffer();

        int i = 0;
        int j;
        int k;

        while (true) {
            j = val.indexOf(DELIM_START, i);

            if (j == -1) {
                // no more variables
                if (i == 0) { // this is a simple string

                    return val;
                } else { // add the tail string which contails no variables and return the result.
                    sbuf.append(val.substring(i, val.length()));

                    return sbuf.toString();
                }
            } else {
                sbuf.append(val.substring(i, j));
                k = val.indexOf(DELIM_STOP, j);

                if (k == -1) {
                    throw new IllegalArgumentException(
                            '"' + val + "\" has no closing brace. Opening brace at position "
                                    + j + '.');
                } else {
                    j += DELIM_START_LEN;

                    String rawKey = val.substring(j, k);

                    // Massage the key to extract a default replacement if there is one
                    String[] extracted = extractDefaultReplacement(rawKey);
                    String key = extracted[0];
                    String defaultReplacement = extracted[1]; // can be null

                    String replacement = null;

                    // first try the props passed as parameter
                    if (props != null) {
                        replacement = props.getProperty(key);
                    }

                    // then try in System properties
                    if (replacement == null) {
                        replacement = getSystemProperty(key, null);
                    }

                    // if replacement is still null, use the defaultReplacement which
                    // still be null
                    if (replacement == null) {
                        replacement = defaultReplacement;
                    }

                    if (replacement != null) {
                        // Do variable substitution on the replacement string
                        // such that we can solve "Hello ${x2}" as "Hello p1"
                        // where the properties are
                        // x1=p1
                        // x2=${x1}
                        String recursiveReplacement = substVars(replacement, props);
                        sbuf.append(recursiveReplacement);
                    }

                    i = k + DELIM_STOP_LEN;
                }
            }
        }
    }

    static public String[] extractDefaultReplacement(String key) {
        String[] result = new String[2];
        result[0] = key;
        int d = key.indexOf(":-");
        if (d != -1) {
            result[0] = key.substring(0, d);
            result[1] = key.substring(d + 2);
        }
        return result;
    }


    public static String stripDuplicateBackslashes(final String src) {
        int i = src.lastIndexOf('\\');
        if (i > 0) {
            StringBuffer buf = new StringBuffer(src);
            for (; i > 0; i = src.lastIndexOf('\\', i - 1)) {
                //
                //  if the preceding character is a slash then
                //     remove the preceding character
                //     and continue processing with the earlier part of the string
                if (src.charAt(i - 1) == '\\') {
                    buf.deleteCharAt(i);
                    i--;
                    if (i == 0) break;
                } else {
                    //
                    //  if there was a single slash then
                    //    the string was not trying to work around
                    //    convertSpecialChars
                    //
                    return src;
                }
            }
            return buf.toString();
        }
        return src;
    }

//  public static void selectAndConfigure(
//    URL url, String clazz, LoggerRepository repository) {
//    Configurator configurator = null;
//    String filename = url.getFile();
//
//    if ((clazz == null) && (filename != null) && filename.endsWith(".xml")) {
//      clazz = JoranConfigurator.class.getName();
//    }
//
//    if (clazz != null) {
//      Logger logger = repository.getLogger(OptionConverter.class.getName());
//      logger.info("Preferred configurator class: " + clazz);
//
//      configurator =
//        (Configurator) instantiateByClassName(clazz, Configurator.class, null);
//
//      if (configurator == null) {
//        logger.error("Could not instantiate configurator [" + clazz + "].");
//
//        return;
//      }
//    } else {
//      configurator = new PropertyConfigurator();
//    }
//
//    configurator.doConfigure(url, repository);
//    if(configurator instanceof ConfiguratorBase) {
//      ((ConfiguratorBase)configurator).dumpErrors();
//    }
//  }
}
