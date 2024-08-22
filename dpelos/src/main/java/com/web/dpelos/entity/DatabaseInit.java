package com.web.dpelos.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.web.dpelos.repository.DuenoRepository;
import com.web.dpelos.repository.MascotaRepository;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class DatabaseInit implements ApplicationRunner{


    @Autowired
    DuenoRepository duenoRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);

        //mascotaRepository.save(new Mascota("Perro", 21, "https://c.files.bbci.co.uk/48DD/production/_107435681_perro1.jpg", "chanda", 1, sqlDate, true, "ceguera"));
        //duenoRepository.save(new Dueno("13231", "ola", "ola", "ola@gmail.com", "3122323212", "hrtps"));
        //duenoRepository.save(new Dueno("210332323","jorge", "Esteban", "yo@gmail.com", "3122323212", "hrtps"));
    }
    
}
