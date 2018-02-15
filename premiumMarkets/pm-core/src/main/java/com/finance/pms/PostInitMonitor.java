package com.finance.pms;

public class PostInitMonitor {

    private static Object optPostInitSyncObj = new Object();
    private static Boolean optPostInitFinished = false;
    private static Boolean optPostInitStarted = false;


    public static void waitForOptPostInitEnd() {
        if (optPostInitStarted) {
            synchronized (optPostInitSyncObj) {
                while (!optPostInitFinished) {
                    try {
                        optPostInitSyncObj.wait();
                    } catch (InterruptedException e) {
                        
                    }
                }
            }
        }
    }

    public static void stopOptPostInit() {
        synchronized (optPostInitSyncObj) {
            optPostInitFinished = true;
            optPostInitSyncObj.notifyAll();
        }
        optPostInitStarted = false;
    }
    
    public static void startOptPostInit() {
        optPostInitStarted = true;
    }

}
