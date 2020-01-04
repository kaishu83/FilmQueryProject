package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

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

		String user = "student";
		String pass = "student";
		String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
		Connection conn = DriverManager.getConnection(URL, user, pass);

		Film film = null;

		
		git git
		String sql = "";
		PreparedStatement stmt = conn.prepareStatement(sql);

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}
