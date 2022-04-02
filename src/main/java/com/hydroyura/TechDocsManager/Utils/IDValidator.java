package com.hydroyura.TechDocsManager.Utils;

public interface IDValidator {
	
	public static long convertFromStringToLong(String strId) {
		
		long id = -1;
		
		try {
			id = Long.valueOf(strId);
		} catch (NumberFormatException ex) {
			System.out.println("Неверный ID - Ошибка при приведении типов");
			ex.printStackTrace();
		}
		
		
		return id;
		
	}
	
	public static float convertFromStringToFloat(String strId) {
		
		float id = -1;
		
		try {
			id = Float.valueOf(strId);
		} catch (NumberFormatException ex) {
			System.out.println("Неверный ID - Ошибка при приведении типов");
			ex.printStackTrace();
		}
		
		
		return id;
		
	}

}
