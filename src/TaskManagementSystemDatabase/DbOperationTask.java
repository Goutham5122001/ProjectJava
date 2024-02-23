package TaskManagementSystemDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DbOperationTask {
	private static final String host = "jdbc:mysql://localhost:3306/tasksdb";
	private static final String username = "root";
	private static final String password = "Goutham$1234";

	// Connection method
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void toAddTasks(Task taskData) {
		try {
			Connection con = getConnection();
			String query = "Insert into task(taskName,taskDeadLine,taskPriority,taskStatus) values(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, taskData.taskName);
			stmt.setString(2, taskData.taskDeadLine);
			stmt.setInt(3, taskData.taskPriority);
			stmt.setString(4, taskData.taskStatus);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			System.out.println("Task Creation Successfully !!!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<Integer, Task> toGetAllTaskData() {
		try {
			Connection con = getConnection();
			String query = "Select * from task";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet result = stmt.executeQuery(query);
			HashMap<Integer, Task> taskDatabase = new HashMap<>();
			while (result.next()) {
				Task obj = new Task();
				obj.taskId = result.getInt(1);
				obj.taskName = result.getString(2);
				obj.taskDeadLine = result.getString(3);
				obj.taskPriority = result.getInt(4);
				obj.taskStatus = result.getString(5);
				taskDatabase.put(obj.taskId, obj);
			}
			stmt.close();
			con.close();
			return taskDatabase;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean taskExists(int id) {
		try {
			Connection con = getConnection();
			String query = "select*from task where taskid=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			boolean flag = result.next();
			con.close();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void toRemoveTask(int id) {
		try {
			Connection con = getConnection();
			String query = "Delete from task where taskid = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			System.out.println("Task With " + id + " is removed successfully!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Task toSearchTask(int id) {

		try {
			Connection con = getConnection();
			String query = "Select * from task where taskid=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			Task taskData = new Task();
			result.next();
			taskData.taskId = result.getInt(1);
			taskData.taskName = result.getString(2);
			taskData.taskDeadLine = result.getString(3);
			taskData.taskPriority = result.getInt(4);
			taskData.taskStatus = result.getString(5);
			stmt.close();
			con.close();
			return taskData;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void toUpdateName(int id, String name) {
		try {
			Connection con = getConnection();
			String query = "Update task set taskName=? where taskid=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			System.out.println("Task name is updated Successfully!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateDeadLine(int id, String dead) {
		try {
			Connection con = getConnection();
			String query = "Update task set taskDeadLine=? where taskid=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, dead);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			System.out.println("Task DeadLine is updated Successfully!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void toUpdatePriority(int id, int Priority) {
		try {
			Connection con = getConnection();
			String query = "Update task set taskPriority=? where taskid=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, Priority);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			System.out.println("Task Priority is updated Successfully!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void toUpdateStatus(int id, String Status) {
		try {
			Connection con = getConnection();
			String query = "Update task set taskStatus=? where taskid=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, Status);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			System.out.println("Task Status is updated Successfully!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}