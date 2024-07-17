package com.rias.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rias.literalura.principal.Principal;
// import com.rias.literalura.repository.LibroRepository;
import com.rias.literalura.repository.AutorRepository;
import com.rias.literalura.repository.LibroRepository;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner
{
	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		// Principal principal = new Principal(libroRepository);
		Principal principal = new Principal(libroRepository,autorRepository);
		principal.mostrarMenu();
	}
}
