package com.web.dpelos.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

        private static final Mascota List = null;

        @Autowired
        DuenoRepository duenoRepository;

        @Autowired
        MascotaRepository mascotaRepository;

        @Override
        public void run(ApplicationArguments args) throws Exception {

                LocalDate date = LocalDate.now();
                Date sqlDate = Date.valueOf(date);

                Dueno[] duenos = new Dueno[50];
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
                duenos[10] = new Dueno("48576", "María", "López", "maria.lopez@gmail.com", "3221098765",
                                "https://randomuser.me/api/portraits/women/1.jpg");
                duenos[11] = new Dueno("57684", "José", "Ramírez", "jose.ramirez@gmail.com", "3232109876",
                                "https://randomuser.me/api/portraits/men/1.jpg");
                duenos[12] = new Dueno("67845", "Elena", "Torres", "elena.torres@gmail.com", "3243210987",
                                "https://randomuser.me/api/portraits/women/2.jpg");
                duenos[13] = new Dueno("78956", "Miguel", "Vargas", "miguel.vargas@gmail.com", "3254321098",
                                "https://randomuser.me/api/portraits/men/2.jpg");
                duenos[14] = new Dueno("89067", "Lucía", "Castro", "lucia.castro@gmail.com", "3265432109",
                                "https://randomuser.me/api/portraits/women/3.jpg");
                duenos[15] = new Dueno("90178", "Raúl", "Gutiérrez", "raul.gutierrez@gmail.com", "3276543210",
                                "https://randomuser.me/api/portraits/men/3.jpg");
                duenos[16] = new Dueno("12345", "Patricia", "Mendoza", "patricia.mendoza@gmail.com", "3287654321",
                                "https://randomuser.me/api/portraits/women/4.jpg");
                duenos[17] = new Dueno("23456", "Fernando", "Rojas", "fernando.rojas@gmail.com", "3298765432",
                                "https://randomuser.me/api/portraits/men/4.jpg");
                duenos[18] = new Dueno("34567", "Gabriela", "Santos", "gabriela.santos@gmail.com", "3309876543",
                                "https://randomuser.me/api/portraits/women/5.jpg");
                duenos[19] = new Dueno("45678", "Ricardo", "Ortega", "ricardo.ortega@gmail.com", "3310987654",
                                "https://randomuser.me/api/portraits/men/5.jpg");
                duenos[20] = new Dueno("56789", "Natalia", "Silva", "natalia.silva@gmail.com", "3321098765",
                                "https://randomuser.me/api/portraits/women/6.jpg");
                duenos[21] = new Dueno("67890", "Andrés", "Morales", "andres.morales@gmail.com", "3332109876",
                                "https://randomuser.me/api/portraits/men/6.jpg");
                duenos[22] = new Dueno("78901", "Claudia", "Herrera", "claudia.herrera@gmail.com", "3343210987",
                                "https://randomuser.me/api/portraits/women/7.jpg");
                duenos[23] = new Dueno("89012", "Juan", "Pérez", "juan.perez@gmail.com", "3354321098",
                                "https://randomuser.me/api/portraits/men/7.jpg");
                duenos[24] = new Dueno("90123", "Laura", "García", "laura.garcia@gmail.com", "3365432109",
                                "https://randomuser.me/api/portraits/women/8.jpg");
                duenos[25] = new Dueno("01234", "Carlos", "Martínez", "carlos.martinez@gmail.com", "3376543210",
                                "https://randomuser.me/api/portraits/men/8.jpg");
                duenos[26] = new Dueno("12346", "Ana", "Gómez", "ana.gomez@gmail.com", "3387654321",
                                "https://randomuser.me/api/portraits/women/9.jpg");
                duenos[27] = new Dueno("23457", "Jorge", "López", "jorge.lopez@gmail.com", "3398765432",
                                "https://randomuser.me/api/portraits/men/9.jpg");
                duenos[28] = new Dueno("34568", "Sofía", "Ramírez", "sofia.ramirez@gmail.com", "3409876543",
                                "https://randomuser.me/api/portraits/women/10.jpg");
                duenos[29] = new Dueno("45679", "Luis", "Torres", "luis.torres@gmail.com", "3410987654",
                                "https://randomuser.me/api/portraits/men/10.jpg");
                duenos[30] = new Dueno("56780", "Marta", "Vargas", "marta.vargas@gmail.com", "3421098765",
                                "https://randomuser.me/api/portraits/women/11.jpg");
                duenos[31] = new Dueno("67891", "Pedro", "Castro", "pedro.castro@gmail.com", "3432109876",
                                "https://randomuser.me/api/portraits/men/11.jpg");
                duenos[32] = new Dueno("78902", "Isabel", "Gutiérrez", "isabel.gutierrez@gmail.com", "3443210987",
                                "https://randomuser.me/api/portraits/women/12.jpg");
                duenos[33] = new Dueno("89013", "Andrés", "Mendoza", "andres.mendoza@gmail.com", "3454321098",
                                "https://randomuser.me/api/portraits/men/12.jpg");
                duenos[34] = new Dueno("90124", "María", "Rojas", "maria.rojas@gmail.com", "3465432109",
                                "https://randomuser.me/api/portraits/women/13.jpg");
                duenos[35] = new Dueno("01235", "José", "Santos", "jose.santos@gmail.com", "3476543210",
                                "https://randomuser.me/api/portraits/men/13.jpg");
                duenos[36] = new Dueno("12347", "Elena", "Ortega", "elena.ortega@gmail.com", "3487654321",
                                "https://randomuser.me/api/portraits/women/14.jpg");
                duenos[37] = new Dueno("23458", "Miguel", "Silva", "miguel.silva@gmail.com", "3498765432",
                                "https://randomuser.me/api/portraits/men/14.jpg");
                duenos[38] = new Dueno("34569", "Lucía", "Morales", "lucia.morales@gmail.com", "3509876543",
                                "https://randomuser.me/api/portraits/women/15.jpg");
                duenos[39] = new Dueno("45680", "Raúl", "Herrera", "raul.herrera@gmail.com", "3510987654",
                                "https://randomuser.me/api/portraits/men/15.jpg");
                duenos[40] = new Dueno("56791", "Patricia", "Pérez", "patricia.perez@gmail.com", "3521098765",
                                "https://randomuser.me/api/portraits/women/16.jpg");
                duenos[41] = new Dueno("67892", "Fernando", "García", "fernando.garcia@gmail.com", "3532109876",
                                "https://randomuser.me/api/portraits/men/16.jpg");
                duenos[42] = new Dueno("78903", "Gabriela", "Martínez", "gabriela.martinez@gmail.com", "3543210987",
                                "https://randomuser.me/api/portraits/women/17.jpg");
                duenos[43] = new Dueno("89014", "Ricardo", "Gómez", "ricardo.gomez@gmail.com", "3554321098",
                                "https://randomuser.me/api/portraits/men/17.jpg");
                duenos[44] = new Dueno("90125", "Natalia", "López", "natalia.lopez@gmail.com", "3565432109",
                                "https://randomuser.me/api/portraits/women/18.jpg");
                duenos[45] = new Dueno("01236", "Andrés", "Ramírez", "andres.ramirez@gmail.com", "3576543210",
                                "https://randomuser.me/api/portraits/men/18.jpg");
                duenos[46] = new Dueno("12348", "Claudia", "Torres", "claudia.torres@gmail.com", "3587654321",
                                "https://randomuser.me/api/portraits/women/19.jpg");
                duenos[47] = new Dueno("23459", "Juan", "Vargas", "juan.vargas@gmail.com", "3598765432",
                                "https://randomuser.me/api/portraits/men/19.jpg");
                duenos[48] = new Dueno("34570", "Laura", "Castro", "laura.castro@gmail.com", "3609876543",
                                "https://randomuser.me/api/portraits/women/20.jpg");
                duenos[49] = new Dueno("45681", "Carlos", "Gutiérrez", "carlos.gutierrez@gmail.com", "3610987654",
                                "https://randomuser.me/api/portraits/men/20.jpg");

                // Inicializa el arreglo de mascotas
                Mascota[] mascotas = new Mascota[] {
                new Mascota("Firulais", 2,
                                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6vK9XGVpsZJyLqihEWrl8FZlRTEIvPpn90KC5OnJRh2qiNiEzy0JBlls0ZV3_rkQOmdo&usqp=CAU",
                                "Pastor Aleman", sqlDate, true, "Displasia de cadera"),
                new Mascota("Toby", 3,
                                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                                "Labrador", sqlDate, true, "Alergias cutáneas"),
                new Mascota("Rex", 4,
                                "https://i.pinimg.com/1200x/07/84/ab/0784ab30e8c3c18e909620e531572b53.jpg",
                                "Pitbull", sqlDate,
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
                                "https://cdn.britannica.com/07/234207-050-0037B589/English-bulldog-dog.jpg",
                                "Bulldog", sqlDate,
                                true, "Alergias alimentarias"),
                new Mascota("Luna", 2,
                                "https://www.dogster.com/wp-content/uploads/2024/01/1onbcf7aody.jpg",
                                "Poodle", sqlDate, true, "Enfermedad dental"),
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
                                "https://cdn.britannica.com/69/234469-050-B883797B/Rottweiler-dog.jpg",
                                "Rottweiler", sqlDate,
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
                                "https://cdn.britannica.com/71/234471-050-093F4211/shiba-inu-dog-in-the-snow.jpg",
                                "Shiba Inu",
                                sqlDate, true, "Enfermedad de la piel"),
                new Mascota("Buddy", 2,
                                "https://cdn.britannica.com/68/234468-050-3A9A1D8C/Golden-Retriever-dog.jpg",
                                "Golden Retriever", sqlDate, true, "Artritis"),
                new Mascota("Duke", 3,
                                "https://cdn.britannica.com/69/234469-050-B883797B/Rottweiler-dog.jpg",
                                "Rottweiler", sqlDate, true, "Displasia de cadera"),
                new Mascota("Zeus", 4,
                                "https://cdn.britannica.com/70/234470-050-3A9A1D8C/German-Shepherd-dog.jpg",
                                "Pastor Alemán", sqlDate, true, "Epilepsia"),
                new Mascota("Oscar", 5,
                                "https://cdn.britannica.com/71/234471-050-093F4211/shiba-inu-dog-in-the-snow.jpg",
                                "Shiba Inu", sqlDate, true, "Alergias"),
                new Mascota("Sam", 2,
                                "https://cdn.britannica.com/72/234472-050-3A9A1D8C/Poodle-dog.jpg",
                                "Poodle", sqlDate, true, "Problemas dentales"),
                new Mascota("Jack", 3,
                                "https://cdn.britannica.com/73/234473-050-3A9A1D8C/Beagle-dog.jpg",
                                "Beagle", sqlDate, true, "Obesidad"),
                new Mascota("Daisy", 4,
                                "https://cdn.britannica.com/74/234474-050-3A9A1D8C/Bulldog-dog.jpg",
                                "Bulldog", sqlDate, true, "Problemas respiratorios"),
                new Mascota("Lola", 5,
                                "https://cdn.britannica.com/75/234475-050-3A9A1D8C/Boxer-dog.jpg",
                                "Boxer", sqlDate, true, "Dermatitis"),
                new Mascota("Maggie", 2,
                                "https://cdn.britannica.com/76/234476-050-3A9A1D8C/Dachshund-dog.jpg",
                                "Dachshund", sqlDate, true, "Problemas de columna"),
                new Mascota("Riley", 3,
                                "https://cdn.britannica.com/77/234477-050-3A9A1D8C/Chihuahua-dog.jpg",
                                "Chihuahua", sqlDate, true, "Hipoglucemia"),
                new Mascota("Sophie", 4,
                                "https://cdn.britannica.com/78/234478-050-3A9A1D8C/Pomeranian-dog.jpg",
                                "Pomeranian", sqlDate, true, "Hipotiroidismo"),
                new Mascota("Buster", 5,
                                "https://cdn.britannica.com/79/234479-050-3A9A1D8C/Bichon-Frise-dog.jpg",
                                "Bichón Frisé", sqlDate, true, "Trastornos digestivos"),
                new Mascota("Chloe", 2,
                                "https://cdn.britannica.com/80/234480-050-3A9A1D8C/Cocker-Spaniel-dog.jpg",
                                "Cocker Spaniel", sqlDate, true, "Otitis"),
                new Mascota("Max", 3,
                                "https://cdn.britannica.com/81/234481-050-3A9A1D8C/Great-Dane-dog.jpg",
                                "Gran Danés", sqlDate, true, "Tumores"),
                new Mascota("Bella", 4,
                                "https://cdn.britannica.com/82/234482-050-3A9A1D8C/Dalmatian-dog.jpg",
                                "Dálmata", sqlDate, true, "Sordera"),
                new Mascota("Charlie", 5,
                                "https://cdn.britannica.com/83/234483-050-3A9A1D8C/Bulldog-Francés-dog.jpg",
                                "Bulldog Francés", sqlDate, true, "Problemas respiratorios"),
                new Mascota("Lucy", 2,
                                "https://cdn.britannica.com/84/234484-050-3A9A1D8C/Husky-Siberiano-dog.jpg",
                                "Husky Siberiano", sqlDate, true, "Cataratas"),
                new Mascota("Cooper", 3,
                                "https://cdn.britannica.com/85/234485-050-3A9A1D8C/English-Bulldog-dog.jpg",
                                "Bulldog Inglés", sqlDate, true, "Alergias alimentarias"),
                new Mascota("Luna", 4,
                                "https://cdn.britannica.com/86/234486-050-3A9A1D8C/Poodle-dog.jpg",
                                "Poodle", sqlDate, true, "Enfermedad dental"),
                new Mascota("Rocky", 5,
                                "https://cdn.britannica.com/87/234487-050-3A9A1D8C/Boxer-dog.jpg",
                                "Boxer", sqlDate, true, "Eczema"),
                new Mascota("Daisy", 2,
                                "https://cdn.britannica.com/88/234488-050-3A9A1D8C/Dalmatian-dog.jpg",
                                "Dálmata", sqlDate, true, "Diabetes"),
                new Mascota("Bailey", 3,
                                "https://cdn.britannica.com/89/234489-050-3A9A1D8C/Corgi-dog.jpg",
                                "Corgi", sqlDate, true, "Artritis"),
                new Mascota("Milo", 4,
                                "https://cdn.britannica.com/90/234490-050-3A9A1D8C/Shih-Tzu-dog.jpg",
                                "Shih Tzu", sqlDate, true, "Dermatitis"),
                new Mascota("Sadie", 5,
                                "https://cdn.britannica.com/91/234491-050-3A9A1D8C/Chihuahua-dog.jpg",
                                "Chihuahua", sqlDate, true, "Hipoglucemia"),
                new Mascota("Leo", 2,
                                "https://cdn.britannica.com/92/234492-050-3A9A1D8C/Rottweiler-dog.jpg",
                                "Rottweiler", sqlDate, true, "Displasia de cadera"),
                new Mascota("Molly", 3,
                                "https://cdn.britannica.com/93/234493-050-3A9A1D8C/Husky-dog.jpg",
                                "Husky", sqlDate, true, "Epilepsia"),
                new Mascota("Maximus", 4,
                                "https://cdn.britannica.com/94/234494-050-3A9A1D8C/Great-Dane-dog.jpg",
                                "Gran Danés", sqlDate, true, "Tumores"),
                new Mascota("Coco", 5,
                                "https://cdn.britannica.com/95/234495-050-3A9A1D8C/Pomeranian-dog.jpg",
                                "Pomeranian", sqlDate, true, "Hipotiroidismo"),
                new Mascota("Oliver", 2,
                                "https://cdn.britannica.com/96/234496-050-3A9A1D8C/Bichon-Frise-dog.jpg",
                                "Bichón Frisé", sqlDate, true, "Trastornos digestivos"),
                new Mascota("Lola", 3,
                                "https://cdn.britannica.com/97/234497-050-3A9A1D8C/Shiba-Inu-dog.jpg",
                                "Shiba Inu", sqlDate, true, "Enfermedad de la piel"),
                new Mascota("Buddy", 4,
                                "https://cdn.britannica.com/98/234498-050-3A9A1D8C/Golden-Retriever-dog.jpg",
                                "Golden Retriever", sqlDate, true, "Artritis"),
                new Mascota("Duke", 5,
                                "https://cdn.britannica.com/99/234499-050-3A9A1D8C/Rottweiler-dog.jpg",
                                "Rottweiler", sqlDate, true, "Displasia de cadera"),
                new Mascota("Zeus", 2,
                                "https://cdn.britannica.com/100/234500-050-3A9A1D8C/German-Shepherd-dog.jpg",
                                "Pastor Alemán", sqlDate, true, "Epilepsia"),
                new Mascota("Oscar", 3,
                                "https://cdn.britannica.com/101/234501-050-3A9A1D8C/Shiba-Inu-dog.jpg",
                                "Shiba Inu", sqlDate, true, "Alergias"),
                new Mascota("Sam", 4,
                                "https://cdn.britannica.com/102/234502-050-3A9A1D8C/Poodle-dog.jpg",
                                "Poodle", sqlDate, true, "Problemas dentales"),
                new Mascota("Jack", 5,
                                "https://cdn.britannica.com/103/234503-050-3A9A1D8C/Beagle-dog.jpg",
                                "Beagle", sqlDate, true, "Obesidad"),
                new Mascota("Daisy", 2,
                                "https://cdn.britannica.com/104/234504-050-3A9A1D8C/Bulldog-dog.jpg",
                                "Bulldog", sqlDate, true, "Problemas respiratorios"),
                new Mascota("Lola", 3,
                                "https://cdn.britannica.com/105/234505-050-3A9A1D8C/Boxer-dog.jpg",
                                "Boxer", sqlDate, true, "Dermatitis"),
                new Mascota("Maggie", 4,
                                "https://cdn.britannica.com/106/234506-050-3A9A1D8C/Dachshund-dog.jpg",
                                "Dachshund", sqlDate, true, "Problemas de columna"),
                new Mascota("Riley", 5,
                                "https://cdn.britannica.com/107/234507-050-3A9A1D8C/Chihuahua-dog.jpg",
                                "Chihuahua", sqlDate, true, "Hipoglucemia"),
                new Mascota("Sophie", 2,
                                "https://cdn.britannica.com/108/234508-050-3A9A1D8C/Pomeranian-dog.jpg",
                                "Pomeranian", sqlDate, true, "Hipotiroidismo"),
                new Mascota("Buster", 3,
                                "https://cdn.britannica.com/109/234509-050-3A9A1D8C/Bichon-Frise-dog.jpg",
                                "Bichón Frisé", sqlDate, true, "Trastornos digestivos"),
                new Mascota("Chloe", 4,
                                "https://cdn.britannica.com/110/234510-050-3A9A1D8C/Cocker-Spaniel-dog.jpg",
                                "Cocker Spaniel", sqlDate, true, "Otitis"),
                new Mascota("Max", 5,
                                "https://cdn.britannica.com/111/234511-050-3A9A1D8C/Great-Dane-dog.jpg",
                                "Gran Danés", sqlDate, true, "Tumores"),
                new Mascota("Bella", 2,
                                "https://cdn.britannica.com/112/234512-050-3A9A1D8C/Dalmatian-dog.jpg",
                                "Dálmata", sqlDate, true, "Sordera"),
                new Mascota("Buddy", 2, "https://images.unsplash.com/photo-1558788353-f76d92427f16",
                                "Golden Retriever", sqlDate, true, "Artritis"),
                new Mascota("Duke", 3, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Rottweiler", sqlDate, true, "Displasia de cadera"),
                new Mascota("Zeus", 4, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Pastor Alemán", sqlDate, true, "Epilepsia"),
                new Mascota("Oscar", 5, "https://images.unsplash.com/photo-1592194996308-7d9c3f8d4a2b",
                                "Shiba Inu", sqlDate, true, "Alergias"),
                new Mascota("Sam", 2, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Poodle", sqlDate, true, "Problemas dentales"),
                new Mascota("Jack", 3, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Beagle", sqlDate, true, "Obesidad"),
                new Mascota("Daisy", 4, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Bulldog", sqlDate, true, "Problemas respiratorios"),
                new Mascota("Lola", 5, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Boxer", sqlDate, true, "Dermatitis"),
                new Mascota("Maggie", 2, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Dachshund", sqlDate, true, "Problemas de columna"),
                new Mascota("Riley", 3, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Chihuahua", sqlDate, true, "Hipoglucemia"),
                new Mascota("Sophie", 4, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Pomeranian", sqlDate, true, "Hipotiroidismo"),
                new Mascota("Buster", 5, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Bichón Frisé", sqlDate, true, "Trastornos digestivos"),
                new Mascota("Chloe", 2, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Cocker Spaniel", sqlDate, true, "Otitis"),
                new Mascota("Max", 3, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Gran Danés", sqlDate, true, "Tumores"),
                new Mascota("Bella", 4, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Dálmata", sqlDate, true, "Sordera"),
                new Mascota("Charlie", 5, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Bulldog Francés", sqlDate, true, "Problemas respiratorios"),
                new Mascota("Lucy", 2, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Husky Siberiano", sqlDate, true, "Cataratas"),
                new Mascota("Cooper", 3, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Bulldog Inglés", sqlDate, true, "Alergias alimentarias"),
                new Mascota("Luna", 4, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Poodle", sqlDate, true, "Enfermedad dental"),
                new Mascota("Rocky", 5, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Boxer", sqlDate, true, "Eczema"),
                new Mascota("Daisy", 2, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Dálmata", sqlDate, true, "Diabetes"),
                new Mascota("Bailey", 3, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Corgi", sqlDate, true, "Artritis"),
                new Mascota("Milo", 4, "https://images.unsplash.com/photo-1558788353-f76d92427f16",
                                "Shih Tzu", sqlDate, true, "Dermatitis"),
                new Mascota("Sadie", 5, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Chihuahua", sqlDate, true, "Hipoglucemia"),
                new Mascota("Leo", 2, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Rottweiler", sqlDate, true, "Displasia de cadera"),
                new Mascota("Molly", 3, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Husky", sqlDate, true, "Epilepsia"),
                new Mascota("Maximus", 4, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Gran Danés", sqlDate, true, "Tumores"),
                new Mascota("Coco", 5, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Pomeranian", sqlDate, true, "Hipotiroidismo"),
                new Mascota("Oliver", 2, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Bichón Frisé", sqlDate, true, "Trastornos digestivos"),
                new Mascota("Lola", 3, "https://images.unsplash.com/photo-1592194996308-7d9c3f8d4a2b",
                                "Shiba Inu", sqlDate, true, "Enfermedad de la piel"),
                new Mascota("Buddy", 4, "https://images.unsplash.com/photo-1558788353-f76d92427f16",
                                "Golden Retriever", sqlDate, true, "Artritis"),
                new Mascota("Duke", 5, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Rottweiler", sqlDate, true, "Displasia de cadera"),
                new Mascota("Zeus", 2, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Pastor Alemán", sqlDate, true, "Epilepsia"),
                new Mascota("Oscar", 3, "https://images.unsplash.com/photo-1592194996308-7d9c3f8d4a2b",
                                "Shiba Inu", sqlDate, true, "Alergias"),
                new Mascota("Sam", 4, "https://images.unsplash.com/photo-1560807707-8cc77767d783",
                                "Poodle", sqlDate, true, "Problemas dentales")

                };

                // Ejemplo de uso: guardar las mascotas en un repositorio (si fuera el caso)
                for (Mascota mascota : mascotas) {
                        mascotaRepository.save(mascota);
                }

                for (Dueno dueno : duenos) {
                        duenoRepository.save(dueno);
                }

                // socicar mascotas a dueños
               /* Dueno asociar = duenoRepository.findById((long) 1).get();
                for (Mascota masc : mascotaRepository.findAll()) {
                        masc.setDueno(asociar);
                        mascotaRepository.save(masc);
                }*/

                int CANT_DUENOS=50;
                int CANT_ASIGNACIONES=2;
                // for (Dueno dueno :duenoRepository.findAll()) {
                //     for (int i = 0; i < CANT_ASIGNACIONES; i++) {
                //         int random = ThreadLocalRandom.current().nextInt(1,CANT_MASCOTAS+1);
                //         Long search = Long.valueOf(random);
                //         Mascota masc = mascotaRepository.findById(search).get();
                //         masc.setDueno(dueno);
                //         mascotaRepository.save(masc);
                //     }
                // }
                for (Mascota masc :mascotaRepository.findAll()) {
                        for (int i = 0; i < CANT_ASIGNACIONES; i++) {
                            int random = ThreadLocalRandom.current().nextInt(1,CANT_DUENOS+1);
                            Long search = Long.valueOf(random);
                            Dueno dueno = duenoRepository.findById(search).get();
                            masc.setDueno(dueno);
                            mascotaRepository.save(masc);
                        }
                    }
        }

}
