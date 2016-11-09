package com.google.android.perftesting.testrules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


public class EnableRepeatSuite implements TestRule {

    private int time;

    public EnableRepeatSuite(int time) {
        this.time = time;
    }

    @Override
    public Statement apply(final Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                Thread threadb = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(time * 1000);
                            SuiteCounter.addNoOfThreadRunnig(-1);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }

                    }
                });
                SuiteCounter.addNoOfThreadRunnig(1);
                threadb.start();

                while (true) {
                    if (SuiteCounter.getNoOfThreadRunnig() == 0)
                        break;
                    statement.evaluate();
                }
            }
        };
    }
}

class SuiteCounter {

    public SuiteCounter() {
    }
    static int totalNoOfThreadRunnig = 0;
    public static int getNoOfThreadRunnig(){
        return totalNoOfThreadRunnig;
    }
    public static void addNoOfThreadRunnig(int No){
        totalNoOfThreadRunnig += No;
    }

}
