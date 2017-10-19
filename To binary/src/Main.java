import java.util.Scanner;

public class Main {
	static int preferredType;
	static byte sign;
	static String type;

	public static void main(String[] args) {
		byte a = 0;
		do {
			Scanner number = new Scanner(System.in);
			String numberOut = "";
			if (number.hasNextInt()) {
				long numberIn = number.nextLong();
				watsTheTypeIntegral(numberIn);
				if (numberIn >= 0) {
					sign = 0;
					numberOut = toBinaryPlus(numberIn);
					numberOut = a(numberOut);
				} else {
					sign = 1;
					numberOut = toBinaryMinus(numberIn);
					numberOut = a(numberOut);
				}

			} else {
				double numberIn = number.nextDouble();
				watsTheTypeFractional();
				numberOut = toBinaryFractional(numberIn);
			}
			StringBuffer sb = new StringBuffer(numberOut);
			for (int i = numberOut.length(); i > 0; i--) {
				if (i % 4 == 0) {
					sb.insert(i, " ");
				}
			}

			System.out.println(type + sb + ", " + toHex(sb));
			System.out.println("Хотите перевести еще одно число? 0 - да, остальное - нет.");
			a = (byte) number.nextInt();
		} while (a == 0);
	}

	public static void watsTheTypeFractional() {
		Scanner type = new Scanner(System.in);
		System.out.println("В какой тип перевести число? FLOAT(4), DOUBLE(5)");
		System.out.println("Введите число от 4 до 5");
		preferredType = type.nextByte();
	}

	public static void watsTheTypeIntegral(long numberIn) {
		byte type = 0;
		if (numberIn >= 0) {
			int a = (int) (numberIn / 128);
			if (a == 0) {
				System.out.println("Type:BYTE");
				type = 0;
			} else if (a > 0 && a < 256) {
				System.out.println("Type:SHORT");
				type = 1;
			} else if (a > 255 && a < 16777216) {
				System.out.println("Type:INT");
				type = 2;
			} else {
				System.out.println("Type:LONG");
				type = 3;
			}
		} else {
			if (numberIn >= -128) {
				System.out.println("Type:BYTE");
				type = 0;
			} else if (numberIn >= -32768) {
				System.out.println("Type:SHORT");
				type = 1;
			} else if (numberIn >= -2147483648) {
				System.out.println("Type:INT");
				type = 2;
			} else {
				System.out.println("Type:LONG");
				type = 3;
			}
		}
		Scanner type1 = new Scanner(System.in);
		byte a = 0;
		switch (type) {
		case 0:
			System.out.println("В какой тип данных необходимо перевести? BYTE(0), SHORT(1), INT(2), LONG(3)");
			System.out.println("Введите число от 0 до 3");
			preferredType = type1.nextByte();
			break;
		case 1:
			System.out.println("В какой тип данных необходимо перевести? SHORT(1), INT(2), LONG(3)");
			System.out.println("Введите число от 1 до 3");
			preferredType = type1.nextByte();
			break;
		case 2:
			System.out.println("В какой тип данных необходимо перевести? INT(2), LONG(3)");
			System.out.println("Введите число от 2 до 3");
			preferredType = type1.nextByte();
			break;
		}
	}

	public static String a(String numberIn) {
		String numberOut = new StringBuffer(numberIn).reverse().toString();

		if (sign == 0) {
			switch (preferredType) {
			case (0):
				for (int i = numberOut.length(); i < 8; i++) {
					numberOut += 0;
				}
				type = "(byte)";
				break;
			case (1):
				for (int i = numberOut.length(); i < 16; i++) {
					numberOut += 0;
				}
				type = "(short)";
				break;
			case (2):
				for (int i = numberOut.length(); i < 32; i++) {
					numberOut += 0;
				}
				type = "(int)";

				break;
			case (3):
				for (int i = numberOut.length(); i < 64; i++) {
					numberOut += 0;
				}
				type = "(long)";
				break;
			}
		} else {
			switch (preferredType) {
			case (0):
				for (int i = numberOut.length(); i < 8; i++) {
					numberOut += 1;
				}
				type = "(byte)";
				break;
			case (1):
				for (int i = numberOut.length(); i < 16; i++) {
					numberOut += 1;
				}
				type = "(short)";

				break;
			case (2):
				for (int i = numberOut.length(); i < 32; i++) {
					numberOut += 1;
				}
				type = "(int)";

				break;
			case (3):
				for (int i = numberOut.length(); i < 64; i++) {
					numberOut += 1;
				}
				type = "(long)";

				break;
			}
		}
		String numberOutFinaly = new StringBuffer(numberOut).reverse().toString();
		return numberOutFinaly;
	}

	public static String toBinaryPlus(long numberIn) {
		String numberOut = "";

		while (numberIn > 1) {
			numberOut += numberIn % 2;
			numberIn = numberIn / 2;
		}
		numberOut += numberIn;
		String numberOutFinaly = new StringBuffer(numberOut).reverse().toString();
		return numberOutFinaly;
	}

	public static String toBinaryMinus(long numberIn) {
		numberIn = numberIn * -1;
		String numberOut = "";

		byte u = 0;

		while (numberIn > 1) {
			switch (u) {
			case 0:
				if (numberIn % 2 == 1) {
					numberOut += 1;
					u = 1;
				} else {
					numberOut += 0;
				}
				numberIn = numberIn / 2;
				break;
			case 1:
				if (numberIn % 2 == 1) {
					numberOut += 0;
				} else {
					numberOut += 1;
				}
				numberIn = numberIn / 2;
			}

		}
		switch (u) {
		case 0:
			if (numberIn == 0) {
				numberOut += 0;
			} else {
				numberOut += 1;
			}
			break;
		case 1:
			if (numberIn == 0) {
				numberOut += 1;
			} else {
				numberOut += 0;
			}
		}

		String numberOutFinaly = new StringBuffer(numberOut).reverse().toString();

		return numberOutFinaly;
	}

	public static String toBinaryFractional(double numberIn) {
		String numberOut = "";
		if (numberIn < 0) {
			numberOut += 1;
			numberIn = numberIn * -1;
		} else {
			numberOut += 0;
		}

		byte exponent = 0;
		if (numberIn >= 2) {
			while (numberIn >= 2) {
				numberIn = numberIn / 10;
				exponent++;
			}

		} else {
			while (numberIn < 1) {
				numberIn = numberIn * 10;
				exponent--;
			}
		}
		String exponent2 = "";
		if (preferredType == 4) {
			exponent = (byte) (exponent + 127);
			exponent2 = exponent(exponent);
		} else {
			int exponent1 = exponent + 1023;
			exponent2 = exponent(exponent1);
		}

		numberOut += exponent2;

		if (numberIn > 1) {
			numberIn--;
		}

		switch (preferredType) {
		case 4:
			for (byte i = 0; i < 23; i++) {
				if (numberIn * 2 > 1) {
					numberOut += 1;
					numberIn = numberIn * 2;
					numberIn--;
				} else {
					numberOut += 0;
					numberIn = numberIn * 2;
				}
			}
			break;
		case 5:
			for (byte i = 0; i < 52; i++) {
				if ((numberIn * 2) > 1) {
					numberOut += 1;
					numberIn = numberIn * 2;
					numberIn--;
				} else {
					numberOut += 0;
					numberIn = numberIn * 2;
				}
			}

		}
		return numberOut;
	}

	public static String exponent(int exponentIn) {
		String exponentOut = "";
		if (exponentIn < 0) {
			exponentOut = toBinaryMinus(exponentIn);
			String sb = new StringBuffer(exponentOut).reverse().toString();
			if (preferredType == 4) {
				while (sb.length() < 8) {
					sb += 1;
				}
				type = "(float)";
			} else {
				while (sb.length() < 11) {
					sb += 1;
				}
				type = "(double)";
			}
			exponentOut = new StringBuffer(sb).reverse().toString();
		} else {
			exponentOut = toBinaryPlus(exponentIn);
			String sb = new StringBuffer(exponentOut).reverse().toString();
			if (preferredType == 4) {
				while (sb.length() < 8) {
					sb += 0;
				}
				type = "(float)";
			} else {
				while (sb.length() < 11) {
					sb += 0;
				}
				type = "(double)";
			}
			exponentOut = new StringBuffer(sb).reverse().toString();
		}
		return exponentOut;
	}

	public static String toHex(StringBuffer numberIn) {
		String numberIn1 = new StringBuffer(numberIn).toString();
		numberIn1 = numberIn1.replace(" ", "");
		String inHex = "0x";
		int a = 0, b = 4;
		String c;
		byte d = 0;
		while (b <= numberIn1.length()) {
			c = numberIn1.substring(a, b);
			switch (c) {
			case "0000":
				if (d != 0) {
					inHex += "0";
				}
				break;
			case "0001":
				inHex += "1";
				d = 1;
				break;
			case "0010":
				inHex += "2";
				d = 1;
				break;
			case "0011":
				inHex += "3";
				d = 1;
				break;
			case "0100":
				inHex += "4";
				d = 1;
				break;
			case "0101":
				inHex += "5";
				d = 1;
				break;
			case "0110":
				inHex += "6";
				d = 1;
				break;
			case "0111":
				inHex += "7";
				d = 1;
				break;
			case "1000":
				inHex += "8";
				d = 1;
				break;
			case "1001":
				inHex += "9";
				d = 1;
				break;
			case "1010":
				inHex += "A";
				d = 1;
				break;
			case "1011":
				inHex += "B";
				d = 1;
				break;
			case "1100":
				inHex += "C";
				d = 1;
				break;
			case "1101":
				inHex += "D";
				d = 1;
				break;
			case "1110":
				inHex += "E";
				d = 1;
				break;
			case "1111":
				inHex += "F";
				d = 1;
				break;

			}
			a = a + 4;
			b = b + 4;

		}
		return inHex;
	}
}