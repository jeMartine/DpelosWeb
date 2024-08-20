package com.web.dpelos.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dpelos.entity.Dueno;

@Repository
public class DuenoRepository {

    private Map<Integer, Dueno> duenos = new HashMap<>();

    public DuenoRepository() {
        duenos.put(1, new Dueno(1, "Juan", "Perez", "juanperez@gmail.com", "0987654321"));
        duenos.put(2, new Dueno(2, "Maria", "Gomez", "mariagomez@gmail.com", "0987654322"));
        duenos.put(3, new Dueno(3, "Carlos", "Lopez", "carloslopez@gmail.com", "0987654323"));
        duenos.put(4, new Dueno(4, "Ana", "Martinez", "anamartinez@gmail.com", "0987654324"));
        duenos.put(5, new Dueno(5, "Luis", "Garcia", "luisgarcia@gmail.com", "0987654325"));
        duenos.put(6, new Dueno(6, "Laura", "Rodriguez", "laurarodriguez@gmail.com", "0987654326"));
        duenos.put(7, new Dueno(7, "Jose", "Hernandez", "josehernandez@gmail.com", "0987654327"));
        duenos.put(8, new Dueno(8, "Marta", "Fernandez", "martafernandez@gmail.com", "0987654328"));
        duenos.put(9, new Dueno(9, "Pedro", "Sanchez", "pedrosanchez@gmail.com", "0987654329"));
        duenos.put(10, new Dueno(10, "Sofia", "Ramirez", "sofiaramirez@gmail.com", "0987654330"));
        duenos.put(11, new Dueno(11, "Miguel", "Torres", "migueltorres@gmail.com", "0987654331"));
        duenos.put(12, new Dueno(12, "Lucia", "Flores", "luciaflores@gmail.com", "0987654332"));
        duenos.put(13, new Dueno(13, "Javier", "Diaz", "javierdiaz@gmail.com", "0987654333"));
        duenos.put(14, new Dueno(14, "Elena", "Morales", "elenamorales@gmail.com", "0987654334"));
        duenos.put(15, new Dueno(15, "Raul", "Ortega", "raulortega@gmail.com", "0987654335"));
        duenos.put(16, new Dueno(16, "Patricia", "Castro", "patriciacastro@gmail.com", "0987654336"));
        duenos.put(17, new Dueno(17, "Fernando", "Rojas", "fernandorojas@gmail.com", "0987654337"));
        duenos.put(18, new Dueno(18, "Isabel", "Vargas", "isabelvargas@gmail.com", "0987654338"));
        duenos.put(19, new Dueno(19, "Ricardo", "Mendoza", "ricardomendoza@gmail.com", "0987654339"));
        duenos.put(20, new Dueno(20, "Gabriela", "Guerrero", "gabrielaguerrero@gmail.com", "0987654340"));
    }

    /* CRUD DUENO */
    public Dueno getDuenoById(Integer id) {
        return duenos.get(id);
    }

    public Collection<Dueno> getDuenos() {
        return duenos.values();
    }

    public void addDueno(Dueno dueno) {
        int tam = duenos.size();
        int lastId = duenos.get(tam).getCedulaDueno();
        dueno.setCedulaDueno(lastId + 1);
        duenos.put(dueno.getCedulaDueno(), dueno);
    }

    public void updateDueno(Dueno dueno) {
        duenos.put(dueno.getCedulaDueno(), dueno);
    }

    public void deleteDueno(Integer id) {
        duenos.remove(id);
    }
}