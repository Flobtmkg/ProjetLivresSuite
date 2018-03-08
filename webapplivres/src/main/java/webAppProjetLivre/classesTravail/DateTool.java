package webAppProjetLivre.classesTravail;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTool {
    public static String dateENtoFR(String dateEN){
        String[] tabDate;
        tabDate=dateEN.split("-");
        return tabDate[2]+"/"+tabDate[1]+"/"+tabDate[0];
    }

    public static String dateFRtoEN(String dateFR){
        String[] tabDate;
        tabDate=dateFR.split("/");
        return tabDate[2]+"-"+tabDate[1]+"-"+tabDate[0];
    }

    public static int getAge(String dateEN){
        int theAge;
        String[] tabDate=dateEN.split("-");
        LocalDateTime aujoudhui = LocalDateTime.now();
        LocalDateTime dateInput=LocalDateTime.of(Integer.parseInt(tabDate[0]),Integer.parseInt(tabDate[1]),Integer.parseInt(tabDate[2]),0,0);
        ZoneId idZone=ZoneId.systemDefault();
        long diff=aujoudhui.atZone(idZone).toEpochSecond()-dateInput.atZone(idZone).toEpochSecond();
        theAge=(int)diff/31536000;
        return theAge;
    }
}
