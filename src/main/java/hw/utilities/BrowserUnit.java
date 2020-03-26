package hw.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BrowserUnit {
    public static void wait(int second){
        try {
            Thread.sleep(second*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getRandom(int range){
        Random random = new Random();
        return random.nextInt(range);
    }

    public static int randomReturner(){
        int a ;
        do {
            double s = Math.random();
            a = (int)(s*100);
            if (a<7&&a>=0){
                return a;
            }
        }while (true);
    }



    public static String currentDateReturner(){
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyyMMMMdd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
