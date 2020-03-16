package hw.utilities;

public class BrowserUnit {
    public static void wait(int second){
        try {
            Thread.sleep(second*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
