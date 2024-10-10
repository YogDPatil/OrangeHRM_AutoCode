package com.runner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.WebElement;

public class Run {

	public static void main(String[] args) {
		StringBuilder stringBuilder = null;
		stringBuilder = new StringBuilder();
		String characters = "qwertyuiopasdfghjklzxcvbnm";
		Random random = new Random();
		for (int i = 0; i < 3; i++) {

			char st = characters.charAt(random.nextInt(characters.length()));
			stringBuilder.append(st);

		}
		String[] a = { "Ass", "dd" };
		
		System.out.println(stringBuilder.toString());

		
//		for(WebElement el:records) {
//			if(!el.equals(re)) {
//				if(button.isPresent()) {
//					click();
//				}else {
//					System.out.println("page end");
//				}
//			}else {
//				System.out.println("applied leave is showing");
//			}
//		}
		
	}

}
