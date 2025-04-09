package com.product.api.product.service;

import java.io.IOException;
import java.nio.file.Files;
//esta es la biblioteca correcta
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.product.dto.DtoProductImageIn;
import com.product.api.product.entity.ProductImage;
import com.product.api.product.repository.RepoProductImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcProductImageImp implements SvcProductImage {
	// bean para acceder al repository de product image
	@Autowired
	RepoProductImage repo;

	// guardamos la ruta en una variable
	@Value("${app.upload.dir}")
	private String uploadDir;

	@Override
	public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in) {
		// TODO Auto-generated method stub
		try {
			// Eliminar el prefijo "data:image/png;base64," si existe
			if (in.getImage().startsWith("data:image")) {
				int commaIndex = in.getImage().indexOf(",");
				if (commaIndex != -1) {
					in.setImage(in.getImage().substring(commaIndex + 1));
				}
			}

			// Decodifica la cadena Base64 a bytes
			byte[] imageBytes = Base64.getDecoder().decode(in.getImage());

			// Genera un nombre único para la imagen (se asume extensión PNG)
			String fileName = UUID.randomUUID().toString() + ".png";

			// Construye la ruta completa donde se guardará la imagen
			Path imagePath = Paths.get(uploadDir, "img", "product", fileName);

			// Asegurarse de que el directorio exista
			Files.createDirectories(imagePath.getParent());

			// Escribir el archivo en el sistema de archivos
			Files.write(imagePath, imageBytes);

			// buscamos en la base de datos si hay un product_image con dicho id del
			// producto
			ProductImage productImage = repo.findByProduct_id(in.getProductId());
			if (productImage == null) {
				// si es null tenemos que crear entonces el product image

				// Crear la entidad ProductImage y guardar la URL en la base de datos
				productImage = new ProductImage();
				productImage.setProduct_id(in.getProductId());
				productImage.setImage("img/customer/" + fileName);
				productImage.setStatus(1);

				// Guardar la ruta de la imagen
				repo.save(productImage);
			} else {
				productImage.setImage("img/customer/" + fileName);
				repo.save(productImage);
			}

			return new ResponseEntity<>(new ApiResponse("La imagen del producto ha sido actualizada"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}

	}

}
