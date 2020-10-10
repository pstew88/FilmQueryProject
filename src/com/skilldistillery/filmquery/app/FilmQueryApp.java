package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
//		Scanner input = new Scanner(System.in);
		menu();
//		startUserInterface(input);
//		input.close();
	}

	private void menu() {
		System.out.println("");
		System.out.println("  ***********************************");
		System.out.println("1 ****** Lookup Film by Film ID *****");
		System.out.println("2 **** Lookup Film by Search Word ***");
		System.out.println("3 *************** Exit **************");
		System.out.println("  ***********************************");
		try {
			menuChoice();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void menuChoice() throws SQLException {
		Scanner kb = new Scanner(System.in);
		boolean repeat = true;
		int choice = kb.nextInt();
		while (repeat == true) {

			if (choice == 1) {
				System.out.println("");
				System.out.println("Please enter the ID of the film you would like to look up");
				try {
					int filmId = kb.nextInt();
					Film film = db.findFilmById(filmId);
					if (film != null) {
						System.out.println(film);
					} else {
						System.out.println("Film ID not found");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("");

			} else if (choice == 2) {
				kb.nextLine();
				System.out.println("");
				System.out.println("please enter the Search Word of the film you would like to look up");
				{
					String searchWord = kb.nextLine();
					List<Film> films = db.findFilmBySearch(searchWord);
					if (films.isEmpty()) {
						System.out.println("No results found by Search Words");
					} else {

						for (Film film : films) {
							System.out.println(film);
						}
					}
				}
				System.out.println("");

			} else if (choice == 3) {
				System.out.println("");
				System.out.println("*****************");
				System.out.println("Have a Great Day!");
				System.out.println("*****************");
				repeat = false;
				break;

			} else {
				System.out.println("**************");
				System.out.println("Invalid Option");
				System.out.println("**************");
			}
			menu();
			break;
		}
	}

}
