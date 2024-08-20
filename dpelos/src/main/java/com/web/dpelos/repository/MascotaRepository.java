package com.web.dpelos.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dpelos.entity.Mascota;

/* */
@Repository
public class MascotaRepository {
    private Map<Integer, Mascota> mascotas = new HashMap<>();
    private DuenoRepository duenoRepository = new DuenoRepository();

    public MascotaRepository() {
        mascotas.put(1, new Mascota(1, "Firulais", 2,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6vK9XGVpsZJyLqihEWrl8FZlRTEIvPpn90KC5OnJRh2qiNiEzy0JBlls0ZV3_rkQOmdo&usqp=CAU",
                "Pastor Aleman", 1));
        mascotas.put(2, new Mascota(2, "Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                "Labrador", 2));
        mascotas.put(3, new Mascota(3, "Rex", 4,
                "https://i.pinimg.com/1200x/07/84/ab/0784ab30e8c3c18e909620e531572b53.jpg", "Pitbull", 3));
        mascotas.put(4, new Mascota(4, "Max", 5,
                "https://dogtime.com/wp-content/uploads/sites/12/2024/03/GettyImages-1285465107-e1710251441662.jpg?w=1024",
                "Golden Retriever", 4));
        mascotas.put(5, new Mascota(5, "Bella", 2,
                "https://www.borrowmydoggy.com/_next/image?url=https%3A%2F%2Fcdn.sanity.io%2Fimages%2F4ij0poqn%2Fproduction%2F817123f48402f97455c0e9d3761f79613a151685-650x400.jpg&w=1200&q=100",
                "Bulldog Francés", 5));
        mascotas.put(6, new Mascota(6, "Charlie", 3,
                "https://www.metrovetchicago.com/cdn-cgi/image/q=75,f=auto,metadata=none/sites/default/files/styles/large/public/beagle-dog-breed-info.jpg?itok=xLkF7OZ_",
                "Beagle", 6));
        mascotas.put(7, new Mascota(7, "Lucy", 4,
                "https://images.wagwalkingweb.com/media/daily_wag/blog_articles/hero/1685787498.877709/fun-facts-about-siberian-huskies-1.png",
                "Husky Siberiano", 7));
        mascotas.put(8, new Mascota(8, "Cooper", 1,
                "https://cdn.britannica.com/07/234207-050-0037B589/English-bulldog-dog.jpg", "Bulldog", 8));
        mascotas.put(9, new Mascota(9, "Luna", 2, "https://www.dogster.com/wp-content/uploads/2024/01/1onbcf7aody.jpg",
                "Poodle", 9));
        mascotas.put(10, new Mascota(10, "Rocky", 3,
                "https://media-be.chewy.com/wp-content/uploads/2021/04/16140537/Boxer_Feature-Image.jpg", "Boxer", 10));
        mascotas.put(11, new Mascota(11, "Daisy", 4,
                "https://images.saymedia-content.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:eco%2Cw_1200/MjAxMTY5NTU4ODY4ODYyNDg2/dalmatian-guide.jpg",
                "Dalmata", 11));
        mascotas.put(12, new Mascota(12, "Bailey", 5,
                "https://cdn.prod.website-files.com/6586ad1766809383c71cd41e/658904fc8a695c5c1300ed48_18-National-Welsh-Corgi-Day.jpeg",
                "Corgi", 12));
        mascotas.put(13, new Mascota(13, "Milo", 2,
                "https://media-be.chewy.com/wp-content/uploads/2021/05/13111454/GettyImages-521009590-923x615.jpg",
                "Shih Tzu", 13));
        mascotas.put(14, new Mascota(14, "Sadie", 3,
                "https://image.petmd.com/files/inline-images/chihuahua-sitting.jpg?VersionId=S6qlF.oKQ8gzGp5wlwBf2p1nCzD.PGfQ",
                "Chihuahua", 14));
        mascotas.put(15, new Mascota(15, "Leo", 4,
                "https://cdn.britannica.com/69/234469-050-B883797B/Rottweiler-dog.jpg", "Rottweiler", 15));
        mascotas.put(16, new Mascota(16, "Molly", 1,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiaRyBtoxJoQpYY4C5zk_w_nmkvMXqq39BioPhmUOnvpDmAgsd6QYM66CLefrrBxxYvfw&usqp=CAU",
                "Husky", 16));
        mascotas.put(17, new Mascota(17, "Maximus", 2,
                "https://www.purina.co.uk/sites/default/files/styles/square_medium_440x440/public/2022-07/Great%20Dane1.jpg?h=01c2aecf&itok=tcXXoHXT",
                "Gran Danés", 17));
        mascotas.put(18, new Mascota(18, "Coco", 3,
                "https://media-be.chewy.com/wp-content/uploads/2021/06/02102132/Pomeranian_Featured-Image-1024x615.jpg",
                "Pomeranian", 18));
        mascotas.put(19,
                new Mascota(19, "Oliver", 4,
                        "https://media-be.chewy.com/wp-content/uploads/2021/05/12132826/bichon-frise-3-1024x615.jpg",
                        "Bichón maltés", 19));
        mascotas.put(20, new Mascota(20, "Lola", 5,
                "https://cdn.britannica.com/71/234471-050-093F4211/shiba-inu-dog-in-the-snow.jpg", "Shiba Inu", 20));
    }

    /* Metodos CRUD Mascota */

    public Mascota getMascotaById(Integer id) {
        /* Retorna toda la informacion de la mascota según el id indicado. */
        return mascotas.get(id);
    }

    public Collection<Mascota> getMascotas() {
        /* Retorna toda la informacion de las mascota registradas. */
        return mascotas.values();
    }

    public void addMascota(Mascota mascota) {
        /* Obteniendo el tamaño del mapa mascotas */
        int tam = mascotas.size();
        /* Con el tamaño, obtener el id del útlimo elemento ingresado al mapa */
        int lastId = mascotas.get(tam).getIdMascota();
        /*
         * Asignar el nuevo ID a la mascota a agregar asegurandose que sea +1 del ultimo
         * ID asignado.
         */
        mascota.setIdMascota(lastId + 1);
        /* Insertando la mascota al mapa */
        mascotas.put(mascota.getIdMascota(), mascota);
    }

    public void updateMascota(Mascota mascota) {
        /* Actualiza la información de la mascota correspondiente */
        mascotas.put(mascota.getIdMascota(), mascota);
    }

    public void deleteMascota(Integer id) {
        /* Elimina la informacion de una mascota según su ID */
        mascotas.remove(id);
    }
}
