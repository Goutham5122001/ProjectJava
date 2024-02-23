package TaskManagementSystemDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Task {
	int taskId;
	String taskName;
	String taskDeadLine;
	int taskPriority;
	String taskStatus;

	public static void toInsertATask() {
		System.out.println("Inserting a New Task");
		Task taskData = new Task();
		Scanner input = new Scanner(System.in);

		System.out.println("Task Name: ");
		taskData.taskName = input.next();

		System.out.println("Task Deadline: ");
		taskData.taskDeadLine = input.next();

		System.out.println("Task Priority: ");
		taskData.taskPriority = input.nextInt();

		System.out.println("Task Status: ");
		taskData.taskStatus = input.next();

		DbOperationTask.toAddTasks(taskData);
	}

	public static void toRemoveATask() {
		System.out.println("Removing a Task");
		Scanner input = new Scanner(System.in);

		System.out.print("Enter an ID to be removed: ");
		int id = input.nextInt();

		if (DbOperationTask.taskExists(id)) {
			DbOperationTask.toRemoveTask(id);
			
		} else {
			System.err.println("Task With " + id + " Does Not Exit!!!");
		}
	}

	public static void toUpdateATask() {
		System.out.println("Updating a Task");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter an ID to be Updated: ");
		int id = input.nextInt();

		if (DbOperationTask.taskExists(id)) {
			System.out.println("1. Update Name");
			System.out.println("2. Update Task Deadline");
			System.out.println("3. Update Priority");
			System.out.println("4. Update Status");
			System.out.print("Enter a Choice: ");

			int choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter updated name: ");
				DbOperationTask.toUpdateName(id, input.next());
				break;
			case 2:
				System.out.println("Enter updated Deadline: ");
				DbOperationTask.toUpdateDeadLine(id, input.next());
				break;
			case 3:
				System.out.println("Enter updated Priority: ");
				DbOperationTask.toUpdatePriority(id, input.nextInt());
				break;
			case 4:
				System.out.println("Enter updated Status: ");
				DbOperationTask.toUpdateStatus(id, input.next());
				break;

			default:
				System.err.println("Enter a Valid choice!!!");
				break;
			}
		} else {
			System.err.println("Task with id " + id + " does not exist!!!");
		}
	}

	public static void toDisplayATaskDatabase() {
		HashMap<Integer, Task> tasksdb =DbOperationTask.toGetAllTaskData();
		System.out.println("Displaying All Tasks data");
		System.out.println("*****************");
		System.out.println("ID \t Name \t Deadline \t Priority \t Status");
		System.out.println("*****************");
		for (Map.Entry<Integer, Task> taskEntry :tasksdb.entrySet()) {
			System.out.print(taskEntry.getKey() + "\t");
			System.out.print(taskEntry.getValue().taskName + "\t");
			System.out.print(taskEntry.getValue().taskDeadLine + "\t");
			System.out.print(taskEntry.getValue().taskPriority + "\t");
			System.out.println(taskEntry.getValue().taskStatus + "\t");
			System.out.println("****************");
		}
	}

	public static void toSearchATask() {
		System.out.println("Searching a Task");
		Scanner input = new Scanner(System.in);

		System.out.print("Enter an ID to be Searched: ");
		int id = input.nextInt();

		if (DbOperationTask.taskExists(id)) {
			Task TaskObj = DbOperationTask.toSearchTask(id);
			System.out.println("Task ID            : " + TaskObj.taskId);
			System.out.println("Task Name          : " + TaskObj.taskName);
			System.out.println("Task Deadline      : " + TaskObj.taskDeadLine);
			System.out.println("Task Priority         : " + TaskObj.taskPriority);
			System.out.println("Task Status         : " + TaskObj.taskStatus);
			System.out.println("Task data printed Successfully!!!\n");
		} else {
			System.err.println("Task With id " + id + " does not Exist!!!");
		}
	}
}