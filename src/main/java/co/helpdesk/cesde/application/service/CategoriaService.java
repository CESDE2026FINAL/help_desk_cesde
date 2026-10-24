package co.helpdesk.cesde.application.service;

import java.util.List;

import co.helpdesk.cesde.application.dto.categoria.CategoriaResponse;
import co.helpdesk.cesde.application.dto.categoria.CrearCategoriaRequest;

public interface CategoriaService {

  CategoriaResponse crearCategoria(
      CrearCategoriaRequest request);

  List<CategoriaResponse> listarCategorias();
}