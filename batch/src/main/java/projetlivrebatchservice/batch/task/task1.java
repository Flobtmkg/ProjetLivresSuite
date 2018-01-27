package projetlivrebatchservice.batch.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class task1 {
    @Scheduled(fixedRate=1000)
    public void work() {
        System.out.println("Check!!!");
    }
}
