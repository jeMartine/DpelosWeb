package com.web.dpelos.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.dpelos.entity.Droga;

public interface DrogaService {

    public Droga buscarDrogaPorId(Long id);

    public List<Droga> obtenerDrogas();

    public boolean saveAllExcel(List<Droga> drogas);

    public void venderDroga(Droga droga, int cantidad);

    public Droga addDroga(Droga droga);

    public void deleteDroga(Long id);

    public Droga updateDroga(Droga droga);

    public Page<Droga> buscarMedicamentosPorNombre(String nombreMedicamento, int page, int size);

    public Page<Droga> getMedicamentosPaginadas(int page, int size);

    public long obtenerTotalDrogas();

    public double obtenerTotalVentas();

    public double obtenerTotalGanancias();

    public Long obtenerTotalUnidadesVendidas();
}
