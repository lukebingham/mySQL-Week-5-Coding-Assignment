package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.BaseballDao;
import entity.Baseball;

public class Menu {
	
	private BaseballDao baseballDao;
	private Scanner scanner = new Scanner(System.in);
	private List<String> selections = Arrays.asList(
			"Display Teams", 
			"Display a Team", 
			"Create New Team", 
			"Delete Team",
			"Update Team Location",
			"EXIT");
	
	public void start() {
		
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayTeams();
				} 
				else if (selection.equals("2")) {
					displayTeam();
				} 
				else if (selection.equals("3")) {
					createTeam();
				} 
				else if (selection.equals("4")) {
					deleteTeam();
				} 
				else if (selection.equals("5")) {
					updateTeamLocation();
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		} while (!selection.equals("6"));
	}
	
	private void printMenu() {
		System.out.println("Make a selection: ");
		for (int i = 0; i < selections.size(); i++) {
			System.out.println(i + 1 + "- " + selections.get(i));
		}
	}
	
	private void displayTeams() throws SQLException {
		BaseballDao baseballDao = new BaseballDao();
		List<Baseball> teams = baseballDao.getBaseball();
		for (Baseball team : teams) {
			System.out.println(team.getId() + "- " + team.getLocation() + ", " + team.getName());
		}
	}
	
	private void displayTeam() throws SQLException {
		System.out.println("Enter team id- ");
		int id = Integer.parseInt(scanner.nextLine());
		//System.out.println("test four" + id);
		BaseballDao baseballDao = new BaseballDao();
		//System.out.println("test five");
		Baseball team = baseballDao.getBaseballById(id);
		System.out.println(team.getId() + "- " + team.getLocation() + ", " + team.getName());
	}
	
	private void createTeam() throws SQLException {
		System.out.print("Enter team name: ");
		String name = scanner.nextLine();
		System.out.print("Enter team location: ");
		String location = scanner.nextLine();
		BaseballDao baseballDao = new BaseballDao();
		baseballDao.createTeam(name, location);
	}
	
	private void deleteTeam() throws SQLException {
		System.out.println("Enter team id- ");
		int id = Integer.parseInt(scanner.nextLine());
		BaseballDao baseballDao = new BaseballDao();
		baseballDao.deleteTeam(id);
		
	}
	
	private void updateTeamLocation() throws SQLException {
		System.out.println("Enter team id- ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter new team location: ");
		String location = scanner.nextLine();
		BaseballDao baseballDao = new BaseballDao();
		baseballDao.updateTeamLocation(id, location);
	}
}
