import com.geekbrains.rabbitmq.entity.Nologin;
import com.geekbrains.rabbitmq.repositories.NologinRepository;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootApplication
public class RabbitReaderApplication {
    private final static String QUEUE_NAME = "auth_err";

    public static void main(String[] args) {
        SpringApplication.run(RabbitReaderApplication.class, args);

    }
    /*@Bean
    public CommandLineRunner testApp(NologinRepository nologinRepository) {
        return args -> {
            nologinRepository.save(new Nologin("James"));
            nologinRepository.save(new Nologin("Selena"));

            List<Nologin> allNologin = nologinRepository.findAll();
            System.out.println("All in DB: " + allNologin);

        };
    }*/

    @Bean
    public CommandLineRunner demo(NologinRepository nologinRepository) {
        return new CommandLineRunner() {

            @Override
            @Transactional(readOnly = true)
            public void run(String... args) throws Exception {

                ConnectionFactory connectionFactory = new ConnectionFactory();
                connectionFactory.setHost("localhost");
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();
                channel.queueDeclare(QUEUE_NAME,false,false,false, null);
                System.out.println("waiting message");

                final DeliverCallback deliverCallback = (consumerTag, delivery) ->{
                    String msg = new String(delivery.getBody(),"UTF-8");
                    System.out.println("Receiver msg: "+ msg);
                    nologinRepository.save(new Nologin(msg));
                };
                channel.basicConsume(QUEUE_NAME,true, deliverCallback, consumerTag->{});

            }

        };

    }
}


