package co.helpdesk.cesde.presentation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import co.helpdesk.cesde.application.dto.categoria.CategoryResponse;
import co.helpdesk.cesde.application.dto.categoria.CreateCategoryRequest;
import co.helpdesk.cesde.application.service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoriaService categoriaService;

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<CategoryResponse>
  crearCategoria(
      @Valid
      @RequestBody
      CreateCategoryRequest request) {

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(
            categoriaService
                .crearCategoria(request));
  }

  @GetMapping
  @PreAuthorize(
      "hasAnyRole('ADMIN','SOPORTE','USUARIO')")
  public ResponseEntity<List<CategoryResponse>>
  listarCategorias() {

    return ResponseEntity.ok(
        categoriaService
            .listarCategorias());
  }
}