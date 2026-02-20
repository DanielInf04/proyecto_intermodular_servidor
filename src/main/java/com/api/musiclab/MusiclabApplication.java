package com.api.musiclab;

import com.api.musiclab.entities.*;
import com.api.musiclab.repository.*;
import java.time.LocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MusiclabApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusiclabApplication.class, args);
    }
}
