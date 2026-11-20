package co.helpdesk.cesde.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.helpdesk.cesde.application.dto.categoria.CategoryResponse;
import co.helpdesk.cesde.application.dto.categoria.CreateCategoryRequest;
import co.helpdesk.cesde.application.service.CategoriaService;
import co.helpdesk.cesde.domain.exception.DuplicateCategoryException;
import co.helpdesk.cesde.infrastructure.persistence.entity.CategoryEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.CategoryJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl
    implements CategoriaService {

  private final CategoryJpaRepository categoriaRepository;

  @Override
  public CategoryResponse crearCategoria(
      CreateCategoryRequest request) {

    if (categoriaRepository.existsByNombre(
        request.nombre())) {

      throw new DuplicateCategoryException(
          request.nombre());
    }

    CategoryEntity categoria =
        new CategoryEntity();

    categoria.setNombre(
        request.nombre());

    categoria.setDescripcion(
        request.descripcion());

    categoria.setActivo(true);

    CategoryEntity guardada =
        categoriaRepository.save(categoria);

    return new CategoryResponse(
        guardada.getId(),
        guardada.getNombre(),
        guardada.getDescripcion(),
        guardada.getActivo()
    );
  }

  @Override
  @Transactional(readOnly = true)
  public List<CategoryResponse> listarCategorias() {

    return categoriaRepository.findAll()
        .stream()
        .map(categoria ->
            new CategoryResponse(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getActivo()))
        .toList();
  }
}