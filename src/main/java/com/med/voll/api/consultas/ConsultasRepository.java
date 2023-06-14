package com.med.voll.api.consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ConsultasRepository extends JpaRepository<Consulta, Long> {

    Optional<Consulta> findByIdMedicoAndDataDaConsulta(Long idMedico, LocalDate dataDaConsulta);

    Optional<Consulta> findByIdPacienteAndDataDaConsulta(Long idPaciente, LocalDate dataDaConsulta);
}
