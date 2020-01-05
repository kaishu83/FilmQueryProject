package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private final String user = "student";
	private final String pass = "student";
	private final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static Connection conn;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate,length, replacement_cost, rating, special_features from film where id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rslt = stmt.executeQuery();

			if (rslt.next()) {
				film = new Film();
				film.setId(rslt.getInt("id"));
				film.setTitle(rslt.getNString("title"));
				film.setDescription(rslt.getString("description"));
				film.setReleaseYear(rslt.getInt("release_year"));
				film.setLanguageID(rslt.getInt("language_id"));
				film.setRentalDuration(rslt.getInt("rental_duration"));
				film.setRentalRate(rslt.getDouble("rental_rate"));
				film.setLength(rslt.getInt("length"));
				film.setReplacementCost(rslt.getDouble("replacement_cost"));
				film.setRating(rslt.getString("rating"));
				film.setSpecialFeatures(rslt.getString("special_features"));

			}
			rslt.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "select id, first_name, last_name from actor where id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rslt = stmt.executeQuery();
			if (rslt.next()) {
				actor = new Actor();
				actor.setId(rslt.getInt("id"));
				actor.setFirstName(rslt.getString("first_name"));
				actor.setLastName(rslt.getString("last_name"));
			}
			rslt.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<Actor>();
		Actor actor = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "select actor.id, actor.first_name, actor.last_name from actor join film_actor on actor.id=film_actor.actor_id join film on film.id=film_actor.film_id where film.id=?";

		try {
			actor=new Actor();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rslt = stmt.executeQuery();
			while (rslt.next()) {
				actor.setId(rslt.getInt(1));
				actor.setFirstName(rslt.getString(2));
				actor.setLastName(rslt.getString(3));
				actorList.add(actor);

			}
			rslt.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return actorList;
	}

}
