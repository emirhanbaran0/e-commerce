package com.innova.ecommerce.util;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {

    @Bean
    public  String generateConfirmationCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int randomIndex = random.nextInt(characters.length());
            code.append(characters.charAt(randomIndex));
        }

        return code.toString();
    }

}

