package TaskManagementSystemDatabase;

import java.util.Scanner;


public class TaskManagementSystem {
				
		private static void printMenu() {

			System.out.println("1.Tasks Creation");
			System.out.println("2.Remove a Task ");
			System.out.println("3.Update a Task ");
			System.out.println("4.Display all Task Data ");
			System.out.println("5.Search a Task ");
			System.out.println("6.Exit Application ");
			System.out.print("Enter Your Choice: ");
		}

		public static void main(String[] args) {

			System.out.println("\t\t Task Management System ");
			System.out.println("\t\t---------------------------");

			Scanner input = new Scanner(System.in);
			while (true) {
				printMenu();

				int choice = input.nextInt();
				switch (choice) {
				case 1:
					Task.toInsertATask();
					break;
				case 2:
					Task.toRemoveATask();
					break;
				case 3:
					Task.toUpdateATask();
					break;
				case 4:
					Task.toDisplayATaskDatabase();
					break;
				case 5:
					Task.toSearchATask();
					break;
				case 6:
					System.out.println("Application Exited !!!");
					input.close();
					return;
				default:
					System.err.println(" !!!* Enter a Valid Choice *!!! ");

				}

			}
		}

	}
