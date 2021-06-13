package m2.miage.m2gestionmembres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class M2GestionMembresApplication {

    public static void main(String[] args) {
        SpringApplication.run(M2GestionMembresApplication.class, args);
    }

}
