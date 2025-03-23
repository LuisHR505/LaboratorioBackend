package com.product.api.product.service;

import java.io.IOException;
import java.nio.file.Files;
//por alguna razon importaba una biblioteca diferente que no tenia que ver con lo que 
//necesitabamos, esta es la correcta.
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
import com.product.api.product.dto.DtoCustomerImageIn;
import com.product.api.product.repository.RepoCustomerImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcCustomerImageImp implements SvcCustomerImage {
	@Autowired
	RepoCustomerImage repo;

	@Value("${app.upload.dir}")
	private String uploadDir;

	@Override
	public ResponseEntity<ApiResponse> uploadCustomerImage(DtoCustomerImageIn in) {
		try {

			// primero necesitamos obtener la imagen, para ello comprobamos si
			// dicha imagen tiene el prefijo.
			// Eliminamos el prefijo "data:image/png;base64," si es que existe.
			if (in.getImage().startsWith("data:image")) {
				int commaIndex = in.getImage().indexOf(",");
				if (commaIndex != -1) {
					in.setImage(in.getImage().substring(commaIndex + 1));
				}
			}

			// luego necesitamos pasar la imagen a base 64 para poder guardarla enla bd.
			byte[] imageBytes = Base64.getDecoder().decode(in.getImage());

			// ahora necesitamos un nombre unico para la imagen, dicho nombre se usara como
			// un
			// identificador de la imagen, por eso es unico.
			// Genera un nombre único para la imagen (se asume extensión PNG)
			String fileName = UUID.randomUUID().toString() + ".png";

			// Construimos el path
			// Construye la ruta completa donde se guardará la imagen
			Path imagePath = Paths.get(uploadDir, "img", "customer", fileName);

			// Asegurarse de que el directorio exista
			// aqui creamos la carpeta donde se guardaran las imagenes, si ya existe
			// entonces no hace nada, sin embargo esta linea puede generar excepcione que
			// nuestro catch
			// hasta el momento no atrapa, por eso agregamos un caso mas al catch con una
			// IOexception.
			Files.createDirectories(imagePath.getParent());

			// Escribir el archivo en el sistema de archivos
			Files.write(imagePath, imageBytes);

			return new ResponseEntity<>(new ApiResponse("La imagen del cliente ha sido actualizada"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}
	}

}
