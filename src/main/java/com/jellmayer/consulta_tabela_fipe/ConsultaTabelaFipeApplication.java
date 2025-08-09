package com.jellmayer.consulta_tabela_fipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultaTabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaTabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sistema de consultas à tabela FIPE inicializado.");

		Console console = new Console();
		console.showMenu();
	}
}
