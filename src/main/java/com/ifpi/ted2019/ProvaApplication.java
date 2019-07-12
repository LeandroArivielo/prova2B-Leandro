package com.ifpi.ted2019;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ifpi.ted2019.domain.Medico;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@SpringBootApplication
public class ProvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvaApplication.class, args);		Scanner scanner = new Scanner(System.in);
		String op;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		do {

			System.out.println(menu());
			op = scanner.nextLine();

			String nome;
			String email;
			Integer id;
			switch (op) {
			case "1":
				nome = scanner.nextLine();
				email = scanner.nextLine();
				id = scanner.nextInt();

				cadastrarPessoa(entityManager, nome, email, id);
				break;
			case "2":
				System.out.println(listarRegistros(entityManager));
			case "3":
				nome = scanner.nextLine();
				email = scanner.nextLine();
				id = scanner.nextInt();
				alterarUmRegistro(entityManager, nome, email, id);
			case "4":
				id = scanner.nextInt();
				removerRegistro(entityManager, entityManagerFactory, id);
			default:
				break;

			}
		} while (op != "0");
		entityManager.close();
		entityManagerFactory.close();

	}

	private static void cadastrarPessoa(EntityManager entityManager, String nome, String email, Integer id) {

		Medico medicoFound = entityManager.find(Medico.class, id);
		medicoFound.setNome(nome);
		medicoFound.setEmail(email);
		entityManager.getTransaction().begin();
		entityManager.persist(medicoFound);
		entityManager.getTransaction().commit();

	}

	private static List<Medico> listarRegistros(EntityManager entityManager) {
		String jpql = "SELECT m FROM Pessoa m";
		return entityManager.createQuery(jpql, Medico.class).getResultList();
	}

	private static void alterarUmRegistro(EntityManager entityManager, String nome, String email, Integer id) {
		Medico medicoFound = entityManager.find(Medico.class, id);
		medicoFound.setNome(nome);
		medicoFound.setEmail(email);
		entityManager.getTransaction().begin();
		entityManager.persist(medicoFound);
		entityManager.getTransaction().commit();
	}

	private static void removerRegistro(EntityManager entityManager, EntityManagerFactory entityManagerFactory,
			Integer id) {
		Medico medicoFound = entityManager.find(Medico.class, 1);
		entityManager.getTransaction().begin();
		entityManager.remove(medicoFound);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	public static String menu() {
		String menu = "";
		menu += "Digite 1 para cadastrar um médico.\n";
		menu += "Digite 2 para listar os médicos. \n";
		menu += "Digite 3 para alterar um registro. \n";
		menu += "Digite 4 para remover um registro. \n";
		menu += "Digite 0 para sair";
		return menu;
	}

}