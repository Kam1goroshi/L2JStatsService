package com.l2eminence;

import java.util.concurrent.ConcurrentHashMap;

/**
 * As the name states, this is probably a horrible cache both to performance and security
 * Will update later
 */
public class HorribleCache {
        private static final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        private static long timeStamp = 0;
        private static final Object timeStampLock = new Object();

        public static void resetTimeStamp() {
                synchronized (timeStampLock) {
                        timeStamp = 0;
                }
        }

        public static synchronized boolean shouldUpdateCache() {
                long time = 0;
                synchronized (timeStampLock) {
                        if ((time = System.currentTimeMillis()) - timeStamp > 60000) {
                                timeStamp = time;
                                return true;
                        }
                }
                return false;
        }

        public static void putValue(String key, String value) {
                cache.put(key, value);
        }

        public static String getValue(String key) {
                return cache.get(key);
        }
}