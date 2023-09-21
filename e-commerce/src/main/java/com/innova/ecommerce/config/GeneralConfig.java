package com.innova.ecommerce.config;

import com.innova.ecommerce.util.CodeGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public CodeGenerator confirmationCodeGenerator(){
        return new CodeGenerator();
    }

}
