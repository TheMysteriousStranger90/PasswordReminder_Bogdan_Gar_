package random;

public class OurThread extends Thread{

    private String msg;

    public OurThread() {
    }

    public OurThread(String msg) {
        this.msg = msg;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RandomService.randomSymbolResults.add(msg);
    }
}
