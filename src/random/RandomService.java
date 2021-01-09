package random;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RandomService {

    public static List<String> randomSymbolResults = new CopyOnWriteArrayList<>();

    public static String getRandomSymbol() {
        List<Thread> threads = new LinkedList<>();

        for (Integer i = 0; i < 10; i++) {
            OurThread t = new OurThread(i.toString());
            threads.add(t);
        }

        threads.add(new OurThread("_"));

        for (char c = 65; c <= 122; c++) {
            if(c >= 91 && c <= 96) {
                continue;
            }
            OurThread t = new OurThread(String.valueOf(c));
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = RandomService.randomSymbolResults.get(4);
        RandomService.randomSymbolResults.clear();
        return result;
    }
}