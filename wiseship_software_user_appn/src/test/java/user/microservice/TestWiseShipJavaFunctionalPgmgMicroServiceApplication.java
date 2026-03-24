package user.microservice;

import org.springframework.boot.SpringApplication;

public class TestWiseShipJavaFunctionalPgmgMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(WiseShipUserMicroservice::main).with(TestcontainersConfiguration.class).run(args);
    }

}
