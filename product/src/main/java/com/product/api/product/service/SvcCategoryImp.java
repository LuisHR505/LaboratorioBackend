package com.product.api.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.product.dto.DtoCategoryIn;
import com.product.api.product.entity.Category;
import com.product.api.product.repository.RepoCategory;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcCategoryImp implements SvcCategory {
	@Autowired
	RepoCategory repo;

	@Override
	public ResponseEntity<List<Category>> getCategories() {
		try {
			return new ResponseEntity<>(repo.getCategories(), HttpStatus.OK);

		} catch (Exception DataAccessException) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "No se encontraron las categorias en la base de datos");
		}

	}

	// implementamos el servicio getActiveCategories
	@Override
	public ResponseEntity<List<Category>> getActiveCategories() {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(repo.findByStatusOrderByCategory(1), HttpStatus.OK);

		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}

	}

	// metodo para crear una categoria nueva
	@Override
	public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in) {
		// TODO Auto-generated method stub
		try {
			// insertamos los datos del dto en la base de datos.
			repo.createCategory(in.getCategory(), in.getTag());
			// devolvemos una respuesta al usuario.
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido registrada"), HttpStatus.CREATED);

		} catch (DataAccessException e) {
			// validamos que no haya nombres de categorias/tags repetidos en la bd.
			if (e.getLocalizedMessage().contains("ux_category"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoria ya está registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoria ya está registrado");
			// en caso de error devolvemos una excepcion.
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateCategory(DtoCategoryIn in, Integer id) {
		// TODO Auto-generated method stub
		try {

			validateCategoryId(id);
			// actualizamos en la base de datos la categoria con el id especificado.
			repo.updateCategory(id, in.getCategory(), in.getTag());
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido actualizada"), HttpStatus.OK);
		} catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_categoria"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoria ya está registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoria ya está registrado");

			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> enableCategory(Integer id) {
		// TODO Auto-generated method stub
		try {
			validateCategoryId(id);
			repo.enableCategory(id);
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido activada"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}

	}

	@Override
	public ResponseEntity<ApiResponse> disableCategory(Integer id) {
		// TODO Auto-generated method stub
		try {
			validateCategoryId(id);
			repo.disableCategory(id);
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido desactivada"), HttpStatus.OK);

		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}

	}

	// para obtener una categoria especificada por el id
	@Override
	public ResponseEntity<Category> getCategory(Integer id) {
		try {
			validateCategoryId(id);
			return new ResponseEntity<>(repo.getCategory(id), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	// metodo para validar que existe una categoria en la bd mediante su id.
	private void validateCategoryId(Integer id) {
		try {
			if (repo.getCategory(id) == null) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoria no existe");
			}
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

}
