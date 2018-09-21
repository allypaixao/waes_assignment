package alessandra.assignment.waesAssignment.support;

import java.util.Random;

/** This class will return a randon id to be used on tests **/

public class RandomDataGenerator {

	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvyxwz1234567890";
	private static final int RANDOM_STRING_LENGTH = 100;

	public String getNewID() {
		int id;
		String idFinal;

		id = getRandomNumber();

		// just retuns values greater than 3 as minor values are used on specific tests
		while (id < 3) {
			System.out.println(id);
			id = getRandomNumber();
		}

		idFinal = Integer.toString(id);

		return idFinal;
	}

	/**
	 * This method generates random numbers
	 * 
	 * @return integer
	 */
	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == 1) {
			return randomInt;
		} else {
			return (randomInt - 1);
		}
	}

	/**
	 * This method generates random string
	 * 
	 * @return
	 */
	public String generateRandomString() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public static void main(String a[]) {
		RandomDataGenerator msr = new RandomDataGenerator();
		System.out.println(msr.generateRandomString());
	}

}
