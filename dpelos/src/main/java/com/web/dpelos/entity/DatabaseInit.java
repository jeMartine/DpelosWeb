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
public class DatabaseInit implements ApplicationRunner {

    @Autowired
    DuenoRepository duenoRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);

        Dueno[] duenos = new Dueno[10];
        duenos[0] = new Dueno("13231", "Ana", "Martínez", "ana.martinez@gmail.com", "3001234567",
                "https://static01.nyt.com/images/2017/05/07/arts/07GAL-GADOTweb/07GAL-GADOTweb-articleLarge.jpg?quality=75&auto=webp&disable=upscale");
        duenos[1] = new Dueno("210332323", "Jorge", "Esteban", "jorge.esteban@gmail.com", "3122345678",
                "https://media.gq.com.mx/photos/6203c5ba43f71a078a355054/16:9/w_2560%2Cc_limit/atractivo.jpg");
        duenos[2] = new Dueno("45312", "Laura", "Gómez", "laura.gomez@gmail.com", "3109876543",
                "https://www.mundiario.com/asset/thumbnail,1280,720,center,center/media/mundiario/images/2017/09/10/2017091005043465273.jpg");
        duenos[3] = new Dueno("87456", "Carlos", "Pérez", "carlos.perez@gmail.com", "3156789012",
                "https://media.revistagq.com/photos/6008111d0c66a2a0f048c638/16:9/w_2560%2Cc_limit/chris-hemsworth.jpg");
        duenos[4] = new Dueno("29384", "Sofía", "Rodríguez", "sofia.rodriguez@gmail.com", "3165432109",
                "https://cdn.pixabay.com/photo/2023/07/30/09/12/red-hair-girl-8158373_640.jpg");
        duenos[5] = new Dueno("38475", "Luis", "Fernández", "luis.fernandez@gmail.com", "3176543210",
                "https://www.lolitamoda.com/uploads/post/image/61/56.Reglas_de_estilo_que_todo_hombre_debe_conocer.jpg");
        duenos[6] = new Dueno("65748", "Marta", "García", "marta.garcia@gmail.com", "3187654321",
                "https://fotografias.antena3.com/clipping/cmsimages01/2020/07/21/50A45B0A-59A5-4693-ABFE-CBC7FE467909.png");
        duenos[7] = new Dueno("98234", "Pedro", "Sánchez", "pedro.sanchez@gmail.com", "3198765432",
                "https://media.revistavanityfair.es/photos/60e831737c159c2a681488f5/master/w_1600%2Cc_limit/229873.jpg");
        duenos[8] = new Dueno("18374", "Isabel", "Moreno", "isabel.moreno@gmail.com", "3209876543",
                "https://static.diariofemenino.com/uploads/trabajo/215742-realizarte-mujer.jpg");
        duenos[9] = new Dueno("39485", "Andrés", "Jiménez", "andres.jimenez@gmail.com", "3210987654",
                "https://www.labsaenzrenauld.com/wp-content/uploads/2020/10/Perfil-hombre-ba%CC%81sico_738242395.jpg");

        // Inicializa el arreglo de mascotas
        Mascota[] mascotas = new Mascota[] {
                new Mascota("Firulais", 2,
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6vK9XGVpsZJyLqihEWrl8FZlRTEIvPpn90KC5OnJRh2qiNiEzy0JBlls0ZV3_rkQOmdo&usqp=CAU",
                        "Pastor Aleman", sqlDate, true, "Displasia de cadera"),
                new Mascota("Toby", 3,
                        "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                        "Labrador", sqlDate, true, "Alergias cutáneas"),
                new Mascota("Rex", 4,
                        "https://i.pinimg.com/1200x/07/84/ab/0784ab30e8c3c18e909620e531572b53.jpg", "Pitbull", sqlDate,
                        true, "Infección de oídos"),
                new Mascota("Max", 5,
                        "https://dogtime.com/wp-content/uploads/sites/12/2024/03/GettyImages-1285465107-e1710251441662.jpg?w=1024",
                        "Golden Retriever", sqlDate, true, "Cáncer"),
                new Mascota("Bella", 2,
                        "https://www.borrowmydoggy.com/_next/image?url=https%3A%2F%2Fcdn.sanity.io%2Fimages%2F4ij0poqn%2Fproduction%2F817123f48402f97455c0e9d3761f79613a151685-650x400.jpg&w=1200&q=100",
                        "Bulldog Francés", sqlDate, true, "Problemas respiratorios"),
                new Mascota("Charlie", 3,
                        "https://www.metrovetchicago.com/cdn-cgi/image/q=75,f=auto,metadata=none/sites/default/files/styles/large/public/beagle-dog-breed-info.jpg?itok=xLkF7OZ_",
                        "Beagle", sqlDate, true, "Sobrepeso"),
                new Mascota("Lucy", 4,
                        "https://images.wagwalkingweb.com/media/daily_wag/blog_articles/hero/1685787498.877709/fun-facts-about-siberian-huskies-1.png",
                        "Husky Siberiano", sqlDate, true, "Cataratas"),
                new Mascota("Cooper", 1,
                        "https://cdn.britannica.com/07/234207-050-0037B589/English-bulldog-dog.jpg", "Bulldog", sqlDate,
                        true, "Alergias alimentarias"),
                new Mascota("Luna", 2,
                        "https://www.dogster.com/wp-content/uploads/2024/01/1onbcf7aody.jpg", "Poodle", sqlDate, true,
                        "Enfermedad dental"),
                new Mascota("Rocky", 3,
                        "https://media-be.chewy.com/wp-content/uploads/2021/04/16140537/Boxer_Feature-Image.jpg",
                        "Boxer", sqlDate, true, "Eczema"),
                new Mascota("Daisy", 4,
                        "https://images.saymedia-content.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:eco%2Cw_1200/MjAxMTY5NTU4ODY4ODYyNDg2/dalmatian-guide.jpg",
                        "Dalmata", sqlDate, true, "Diabetes"),
                new Mascota("Bailey", 5,
                        "https://cdn.prod.website-files.com/6586ad1766809383c71cd41e/658904fc8a695c5c1300ed48_18-National-Welsh-Corgi-Day.jpeg",
                        "Corgi", sqlDate, true, "Artritis"),
                new Mascota("Milo", 2,
                        "https://media-be.chewy.com/wp-content/uploads/2021/05/13111454/GettyImages-521009590-923x615.jpg",
                        "Shih Tzu", sqlDate, true, "Dermatitis"),
                new Mascota("Sadie", 3,
                        "https://image.petmd.com/files/inline-images/chihuahua-sitting.jpg?VersionId=S6qlF.oKQ8gzGp5wlwBf2p1nCzD.PGfQ",
                        "Chihuahua", sqlDate, true, "Hipoglucemia"),
                new Mascota("Leo", 4,
                        "https://cdn.britannica.com/69/234469-050-B883797B/Rottweiler-dog.jpg", "Rottweiler", sqlDate,
                        true, "Hip dysplasia"),
                new Mascota("Molly", 1,
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiaRyBtoxJoQpYY4C5zk_w_nmkvMXqq39BioPhmUOnvpDmAgsd6QYM66CLefrrBxxYvfw&usqp=CAU",
                        "Husky", sqlDate, true, "Epilepsia"),
                new Mascota("Maximus", 2,
                        "https://www.purina.co.uk/sites/default/files/styles/square_medium_440x440/public/2022-07/Great%20Dane1.jpg?h=01c2aecf&itok=tcXXoHXT",
                        "Gran Danés", sqlDate, true, "Tumores"),
                new Mascota("Coco", 3,
                        "https://media-be.chewy.com/wp-content/uploads/2021/06/02102132/Pomeranian_Featured-Image-1024x615.jpg",
                        "Pomeranian", sqlDate, true, "Hipotiroidismo"),
                new Mascota("Oliver", 4,
                        "https://media-be.chewy.com/wp-content/uploads/2021/05/12132826/bichon-frise-3-1024x615.jpg",
                        "Bichón maltés", sqlDate, true, "Trastornos digestivos"),
                new Mascota("Lola", 5,
                        "https://cdn.britannica.com/71/234471-050-093F4211/shiba-inu-dog-in-the-snow.jpg", "Shiba Inu",
                        sqlDate, true, "Enfermedad de la piel")
        };

        // Ejemplo de uso: guardar las mascotas en un repositorio (si fuera el caso)
        for (Mascota mascota : mascotas) {
            mascotaRepository.save(mascota);
        }

        for (Dueno dueno : duenos) {
            duenoRepository.save(dueno);
        }

       //socicar mascotas a dueños
       Dueno asociar = duenoRepository.findById((long) 1).get();
        for (Mascota masc : mascotaRepository.findAll()) {
            masc.setDueno(asociar);
            mascotaRepository.save(masc);
        }
    }

}
