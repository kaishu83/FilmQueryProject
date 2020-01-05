package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(4);
		System.out.println(film);

		Actor actor = db.findActorById(2);
		System.out.println(actor);

		List<Actor> actList = db.findActorsByFilmId(2);
		System.out.println(actList);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean stayInLoop = true;

		while (stayInLoop) {
			msg();
			int opt = input.nextInt();
			switch (opt) {
			case 1:
				System.out.println("Please enter a film ID");
				int filmId = input.nextInt();
				Film film = db.findFilmById(filmId);
				try {
					if (film != null) {
						System.out.println(film);
					} else {
						System.out.println("Film of ID " + filmId + " does not exist!");
					}
				} catch (Exception e) {
					System.out.println("Please only enter a Integer!");
				}
				break;

			case 2:
				System.out.println("Please enter your KEYWORDS");
				String keyWords = input.next();
				List<Film> filmList = db.findFilmsByKeywords(keyWords);
				System.out.println(filmList);
				break;
			case 3:
				System.out.println("Goodbye");
				stayInLoop = false;
				break;
			}

		}
	}

	private void msg() {

		System.out.println();
		System.out.println("Welcome to this APP");
		System.out.println("Please choose what you want to do?");
		System.out.println("1.Look up a film by its id.");
		System.out.println("2.Look up a film by a search keyword");
		System.out.println("3.Exit");
	}

}
