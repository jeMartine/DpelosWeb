package com.web.dpelos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.dpelos.dto.VeterinarioDTO;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.RoleRepository;
import com.web.dpelos.repository.UserRepository;
import com.web.dpelos.repository.VeterinarioRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class VeterinarioServiceImplentation implements VeterinarioService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public VeterinarioDTO toDTO(Veterinario veterinario) {
        if (veterinario == null)
            return null;

        return new VeterinarioDTO(
                veterinario.getIdVeterinario(),
                veterinario.getNombreVeterinario(),
                veterinario.getApellidoVeterinario(),
                veterinario.getCedulaVeterinario(),
                veterinario.isEstadoVeterinario(),
                veterinario.getFotoUrl(),
                veterinario.getNumeroAtenciones(),
                veterinario.getEspecialidad() != null ? veterinario.getEspecialidad().getNombreEspecialidad() : null);
    }

    public Veterinario buscarVetPorId(Long id) {
        return veterinarioRepository.findById(id).get();
    }

    @Override
    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Veterinario addVet(Veterinario Vet) {
        //assignUserToVeterinario(Vet);
        return veterinarioRepository.save(Vet);
    }

    @Transactional
    @Override
    public void deleteVet(Long id) {
        Veterinario Vet = veterinarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veterinario no encontrado con el id: " + id));
        ;

        if (Vet != null) {
            veterinarioRepository.deleteById(id);

        }

        // else {
        // throw new NotFoundException();
        // }

    }

    @Transactional
    @Override
    public Veterinario updateVet(Veterinario Vet) {
        return veterinarioRepository.save(Vet);
    }

    @Override
    public Veterinario buscarVetPorCedula(String cedulaVet) {
        return veterinarioRepository.findByCedulaVeterinario(cedulaVet);
    }
    /*@Override
    public Veterinario buscarVetLogin(String cedulaVet, String passwordVet) {
        Optional<UserEntity> userEntityOpt = userRepository.findByUsername(cedulaVet);
        
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            System.out.println("Veterinario encontrado: " + userEntity.getUsername());
            if (passwordEncoder.matches(passwordVet, userEntity.getPassword())) {
                return veterinarioRepository.findByCedulaVeterinario(cedulaVet);
            } else {
                System.out.println("Contraseña incorrecta para veterinario: " + cedulaVet);
            }
        } else {
            System.out.println("Veterinario no encontrado para la cédula: " + cedulaVet);
        }
        return null;
    }*/

    
    /*public Veterinario buscarVetLogin(String cedulaVet, String passwordVet) {
        return veterinarioRepository.findByCedulaVeterinarioAndPasswordVeterinario(cedulaVet, passwordVet);
    }*/
    

    public Page<Veterinario> buscarVeterinarioPorNombre(String nombreVeterinario, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return veterinarioRepository.findByNombreVeterinarioContainingIgnoreCase(nombreVeterinario, pageable);
    }

    public Page<Veterinario> getVeterinarioPaginadas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return veterinarioRepository.findAllVeterinario(pageable);
    }

    public long obtenerTotalVeterinarios() {
        return veterinarioRepository.count();
    }

    public long obtenerTotalVeterinariosActivos() {
        return veterinarioRepository.countByEstadoVeterinarioTrue();
    }

    public long obtenerTotalVeterinariosInactivos() {
        return veterinarioRepository.countByEstadoVeterinarioFalse();
    }

    /*public UserEntity assignUserToVeterinario(Veterinario veterinario) {
        if (veterinario.getUser() == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(veterinario.getCedulaVeterinario()); 
            userEntity.setPassword(passwordEncoder.encode(veterinario.getPasswordVeterinario()));
            Role roles = roleRepository.findByName("VETERINARIO").get();
            userEntity.setRoles(List.of(roles));
            veterinario.setUser(userEntity); 
            userRepository.save(userEntity);
            return userEntity;
        }
        return veterinario.getUser();
    }*/
}
