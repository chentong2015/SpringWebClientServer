package controller.beans;

import java.util.Random;

public class BaseBeanRequestScope {

    private int count = -1;

    public int getCount() {
        return count;
    }

    public int generateRandomCount() {
        Random random = new Random();
        count = random.nextInt(100);
        return count;
    }
}
