package org.example;

public class Calculator {

    public int evaluate(String expression) {
        int sum = 0;
        for (String summand: expression.split("\\+")) {
            sum += Integer.parseInt(summand);
        }
        return sum;
    }

    public boolean longRunningMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }


}
