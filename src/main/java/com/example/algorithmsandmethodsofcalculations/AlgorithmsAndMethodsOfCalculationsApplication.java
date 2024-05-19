package com.example.algorithmsandmethodsofcalculations;

import com.example.algorithmsandmethodsofcalculations.lab7.GreedyAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgorithmsAndMethodsOfCalculationsApplication {

    public static void main(String[] args) {
        GreedyAlgorithm.solve();

        SpringApplication.run(AlgorithmsAndMethodsOfCalculationsApplication.class, args);
    }
}
