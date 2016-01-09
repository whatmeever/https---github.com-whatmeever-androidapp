package com.phone1000;

import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int year = input.nextInt();
		int month = input.nextInt();

		int totalYear = 0, totalMonth = 0, totalDay = 0;

		for (int i = 1900; i < year; i++) {
			if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
				totalYear += 366;
			}else{
				totalYear += 365;
			}
		}
		
		int days = 0;
		for(int i = 1; i <= month; i++){
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;

			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;
			case 2:
				if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
					days = 29;
				}else{
					days = 28;
				}
			default:
				System.out.println("wrong month");
				break;
			}
			
			if(i < month){
				totalMonth += days;
			}
		}
		
		totalDay = totalYear + totalMonth;
		
		System.out.println("日\t一\t二\t三\t四\t五\t六");
		
		int firstDayOfMonth = totalDay % 7 + 1;
		
		for(int i = 0; i < firstDayOfMonth; i++){
			System.out.print("\t");
		}
		
		for(int i = 1; i <= days; i++){
			System.out.print(i + "\t");
			
			if((i + totalDay ) % 7 == 6){
				System.out.print("\n");
			}
		}
	}

}
