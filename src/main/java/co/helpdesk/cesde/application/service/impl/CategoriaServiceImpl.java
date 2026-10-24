package co.helpdesk.cesde.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.helpdesk.cesde.application.dto.categoria.CategoriaResponse;
import co.helpdesk.cesde.application.dto.categoria.CrearCategoriaRequest;
import co.helpdesk.cesde.application.service.CategoriaService;
import co.helpdesk.cesde.domain.exception.CategoriaDuplicadaException;
import co.helpdesk.cesde.infrastructure.persistence.entity.CategoriaEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.CategoriaJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServiceImpl
    implements CategoriaService {

  private final CategoriaJpaRepository categoriaRepository;

  @Override
  public CategoriaResponse crearCategoria(
      CrearCategoriaRequest request) {

    if (categoriaRepository.existsByNombre(
        request.nombre())) {

      throw new CategoriaDuplicadaException(
          request.nombre());
    }

    CategoriaEntity categoria =
        new CategoriaEntity();

    categoria.setNombre(
        request.nombre());

    categoria.setDescripcion(
        request.descripcion());

    categoria.setActivo(true);

    CategoriaEntity guardada =
        categoriaRepository.save(categoria);

    return new CategoriaResponse(
        guardada.getId(),
        guardada.getNombre(),
        guardada.getDescripcion(),
        guardada.getActivo()
    );
  }

  @Override
  @Transactional(readOnly = true)
  public List<CategoriaResponse> listarCategorias() {

    return categoriaRepository.findAll()
        .stream()
        .map(categoria ->
            new CategoriaResponse(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getActivo()))
        .toList();
  }
}