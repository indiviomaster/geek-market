import com.geekbrains.rabbitmq.entity.Nologin;
import com.geekbrains.rabbitmq.repositories.NologinRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootApplication
public class RabbitReaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitReaderApplication.class, args);

    }
    @Bean
    public CommandLineRunner testApp(NologinRepository nologinRepository) {
        return args -> {
            nologinRepository.save(new Nologin("James"));
            nologinRepository.save(new Nologin("Selena"));

            List<Nologin> allNologin = nologinRepository.findAll();
            System.out.println("All in DB: " + allNologin);

        };
    }

    @Bean
    public CommandLineRunner demo(NologinRepository nologinRepository) {
        return new CommandLineRunner() {

            @Override
            @Transactional(readOnly = true)
            public void run(String... args) throws Exception {

                System.out.println("\n1.findAll()...");
                for (Nologin nologin : nologinRepository.findAll()) {
                    System.out.println(nologin);
                }

                System.out.println("Done!");
            }
        };


    }

}


