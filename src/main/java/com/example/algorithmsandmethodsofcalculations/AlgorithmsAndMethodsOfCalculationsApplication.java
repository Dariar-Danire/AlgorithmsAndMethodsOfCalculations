package com.example.algorithmsandmethodsofcalculations;

import com.example.algorithmsandmethodsofcalculations.lab6.HookJeevesMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgorithmsAndMethodsOfCalculationsApplication {

    public static void main(String[] args) {
        HookJeevesMethod.solve();


        SpringApplication.run(AlgorithmsAndMethodsOfCalculationsApplication.class, args);
    }
}
