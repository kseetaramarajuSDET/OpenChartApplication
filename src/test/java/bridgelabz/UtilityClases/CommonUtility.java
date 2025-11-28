package bridgelabz.UtilityClases;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CommonUtility {
	
	
	public static String randomString(int noOfCharacters) {
		return RandomStringUtils.randomAlphabetic(noOfCharacters);
	}
	
	public static String randomNumber(int noOfDigits) {
		return RandomStringUtils.randomNumeric(noOfDigits);
	}
	
	public static String randomPassword(int noOfCharacters) {
		return   RandomStringUtils.randomAlphabetic(noOfCharacters/2)+"@"+RandomStringUtils.randomNumeric(noOfCharacters/2) ;
	}

}
