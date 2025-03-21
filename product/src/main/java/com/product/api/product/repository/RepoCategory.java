package com.product.api.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.product.entity.Category;

import jakarta.transaction.Transactional;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {
	@Query(value = "SELECT * FROM Category ORDER BY category", nativeQuery = true)
	List<Category> getCategories();

	// agregamos los demas querys necesarios para la implementacion de los
	// servicios.

	// encuentra a las categoruas activas por su status y las ordena.
	List<Category> findByStatusOrderByCategory(@Param("status") Integer status);

	// Insertamos una nueva categoria a la bd.
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO category(category, tag, status) VALUES (:category, :tag, 1)", nativeQuery = true)
	void insertarCategory(String category, String tag);

	// actualizamos una categoria en la base de datos.
	// recordemos que @param simplemente es para especificar a que parametro
	// se le asignara la variable que definimos en el query.
	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET category = :category, tag = :tag WHERE category_id = :category_id", nativeQuery = true)
	void updateCategory(@Param("category_id") Integer category_id, @Param("category") String category,
			@Param("tag") String tag);

	//Modificamos el valor del status de una categoria en la base de datos.
	@Modifying
	@Transactional
	@Query(value ="UPDATE category SET status = :status WHERE category_id = :category_id", nativeQuery = true)
	void updateCategoryStatus(@Param("category_id") Integer category_id, @Param("status") Integer status);
	
}
