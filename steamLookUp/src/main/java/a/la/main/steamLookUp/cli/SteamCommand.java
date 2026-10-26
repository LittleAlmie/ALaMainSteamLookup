package a.la.main.steamLookUp.cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class SteamCommand implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        if(args.length == 0) {
            System.out.println("Erreur le psuedo n'est pas bon");
            return;
        }

        String pseudo = args[0];

        System.out.println("recherche avec le pseudo: " + pseudo);
    }
}
