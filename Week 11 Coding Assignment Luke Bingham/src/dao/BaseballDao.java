package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Baseball;

public class BaseballDao {
	
	private Connection connection;
	private final String GET_BASEBALL_QUERY = "select * from baseball";
	private final String GET_BASEBALL_BY_ID_QUERY = "select * from baseball where id = ?";
	private final String CREATE_NEW_BASEBALL_QUERY = "insert into baseball(name, location) values(?, ?)";
	private final String DELETE_BASEBALL_QUERY = "delete from baseball where id = ?";
	private final String UPDATE_BASEBALL_QUERY = "update baseball set location = ? where id = ?";
	
	public BaseballDao() {
		connection = DBconnection.getConnection();
	}
	
	public List<Baseball> getBaseball() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_BASEBALL_QUERY).executeQuery();
		List<Baseball> baseball = new ArrayList<Baseball>();
		
		while (rs.next()) {
			baseball.add(buildBaseball(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		
		return baseball;
	}
	
	public Baseball getBaseballById(int id) throws SQLException {
		//System.out.println("third test");
		PreparedStatement ps = connection.prepareStatement(GET_BASEBALL_BY_ID_QUERY);
		//System.out.println("Second test");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		//System.out.println("this is a test" + rs.getInt(1));
		return buildBaseball(rs.getInt(1), rs.getString(2), rs.getString(3));
	}
	
	private Baseball buildBaseball(int id, String location, String name) {
		return new Baseball(id, location, name);
	}
	
	public void createTeam(String name, String location) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_BASEBALL_QUERY);
		ps.setString(1, name);
		ps.setString(2, location);
		ps.executeUpdate();
	}

	public void deleteTeam(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_BASEBALL_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updateTeamLocation(int id, String location) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_BASEBALL_QUERY);
		ps.setString(1, location);
		ps.setInt(2, id);
		ps.executeUpdate();
	
	}
}
