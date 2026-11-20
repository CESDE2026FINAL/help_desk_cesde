package co.helpdesk.cesde.application.service;

import java.util.List;

import co.helpdesk.cesde.application.dto.categoria.CategoryResponse;
import co.helpdesk.cesde.application.dto.categoria.CreateCategoryRequest;

public interface CategoriaService {

  CategoryResponse crearCategoria(
      CreateCategoryRequest request);

  List<CategoryResponse> listarCategorias();
}