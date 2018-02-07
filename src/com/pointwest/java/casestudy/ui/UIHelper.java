package com.pointwest.java.casestudy.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.java.casestudy.util.Constants;

abstract class UIHelper {
	Logger myLogger = Logger.getLogger(this.getClass());
	final String STRING_NUMBER_SIGN = "#";

	public abstract void displayHeaderUI();

	Scanner sc = new Scanner(System.in);

	public void displayPageName(String pageName) {
		System.out.println(buildRepeatedString("#", 2) + " " + pageName + " " + buildRepeatedString("#", 2));

	}

	public void displayHeader(String headerMessage) {
		String welcomeMessage = buildRepeatedString(" ", 5) + headerMessage;
		System.out.println(buildRepeatedString("-", welcomeMessage.length() + 5));
		System.out.println(welcomeMessage);
		System.out.println(buildRepeatedString("-", welcomeMessage.length() + 5));

	}

	String buildRepeatedString(String string, int count) {
		String buildString = "";

		for (int i = 0; i < count; i++) {
			buildString += string;
		}
		return buildString;
	}

	void displayOptionMenuTemplate(List<String> choices) {
		Iterator<String> iterate = choices.iterator();
		String template = "";
		int i = 0;

		while (iterate.hasNext()) {
			template += "[" + (i + 1) + "] " + iterate.next() + "\n";
			i++;
		}
		System.out.println(template);
	}

	void displayOptionMenuTemplate(String[] choices) {
		String template = "";

		for (int i = 0; i < choices.length; i++) {
			template += "[" + (i + 1) + "] " + choices[i] + "\n";
		}
		System.out.print(template);
	}

	String getInputOption(String[] options) {
		String tempOption = "";
		String optionName = "";
		int selectedOption = 0;
		boolean isOptionCorrect = false;
		do {
			System.out.println(buildRepeatedString("_", 25));
			System.out.print("Enter choice: ");
			tempOption = sc.nextLine().trim();
			System.out.println(buildRepeatedString("-", 25));
			isOptionCorrect = getProcessInput(tempOption, options.length);

			if (!isOptionCorrect) {
				System.out
						.println("-> Invalid Input.Please input number ranging from 1 - " + options.length + " only.");
			}

		} while (!isOptionCorrect);
		selectedOption = Integer.parseInt(tempOption);
		optionName = options[selectedOption - 1];
		return optionName;
	}

	String getInputOption(List<String> options) {
		String tempOption = "";
		String optionName = "";
		int selectedOption = 0;
		boolean isOptionCorrect = false;
		do {
			System.out.print("Enter choice: ");
			tempOption = sc.nextLine().trim();
			System.out.println(buildRepeatedString("-", 25));
			isOptionCorrect = getProcessInput(tempOption, options.size());
			if (!isOptionCorrect) {
				System.out
						.println("-> Invalid Input.Please input number ranging from 1 - " + options.size() + " only.");
			}

		} while (!isOptionCorrect);
		selectedOption = Integer.parseInt(tempOption);
		optionName = options.get(selectedOption - 1);

		return optionName;
	}

	Map<String, String> getInputFields(String[] inputFields) {
		Map<String, String> inputs = new HashMap<String, String>();
		for (int i = 0; i < inputFields.length; i++) {
			System.out.print("Enter " + inputFields[i] + ": ");
			inputs.put(inputFields[i], sc.nextLine());
		}

		return inputs;
	}

	Map<String, String> getInputFields(String[] inputFields, String[] formats) {
		Map<String, String> inputs = new HashMap<String, String>();
		for (int i = 0; i < inputFields.length; i++) {
			
			switch (formats[i]) {
			case Constants.STRING_NUMBER_ONLY:
				inputs.put(inputFields[i], getInputNumberField(inputFields[i]));
				break;
			case Constants.STRING_ALPAHBHET_ONLY:
				inputs.put(inputFields[i], getInputAlphabetField(inputFields[i]));
				break;
			default:
				System.out.print("Enter " + inputFields[i] + ": ");
				inputs.put(inputFields[i], sc.nextLine());
				break;
			}
		}

		return inputs;
	}

	String getInputNumberField(String fieldMessage) {
		String input = "";
		boolean isCorrect = false;

		do {
			System.out.print("Enter " + fieldMessage + ": ");
			input = sc.nextLine();
			if (input.matches("[0-9]+")) {
				isCorrect = true;
			} else {
				System.out.println("Please enter number only.");
			}
		} while (!isCorrect);
		return input;
	}

	String getInputAlphabetField(String fieldMessage) {
		String input = "";
		boolean isCorrect = false;
		do {
			System.out.print("Enter " + fieldMessage + ": ");
			input = sc.nextLine();
			if (input.matches("^[A-z ]+$")) {
				isCorrect = true;
			} else {
				System.out.println("Please enter aplhapbhets only.");
			}
		} while (!isCorrect);
		return input;
	}

	void displayActionOption(String[] actionList) {
		String template = "";
		System.out.println("ACTIONS: ");
		for (int i = 0; i < actionList.length; i++) {
			template += "[" + (i + 1) + "] " + actionList[i] + "\t";

		}
		System.out.println(template);
	}

	boolean getProcessInput(String input, int listSize) {
		boolean isCorrect = false;
		int tempOption = 0;

		if (input.matches("[0-9]+")) {
			tempOption = Integer.parseInt(input);
			if (tempOption > 0 && tempOption <= listSize) {
				isCorrect = true;
			}
		}

		return isCorrect;

	}
}
