import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Zadanie4_StrukturaNawiasowaNowaWersja {

	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(System.in);

		System.out.print("Nazwa pliku-WE tekstowego: ");
		String fwe = scn.next();
		FileReader ifile = null;
		Stack<Character> stos = new Stack<>();

		try {
			ifile = new FileReader(fwe);

			int c; // Następny znak
			int np = 0, nb = 0, nk = 0; // Liczba otwartych '()', '[]', '{}'
			boolean okp = true, okb = true, okk = true; // Na razie wszystko OK

			while ((c = ifile.read()) != -1) {
				//System.out.print((char) c);
				switch (c) {
				case '(':
					++np;
					stos.push((char) c);
					break;
				case ')':
					--np;
					if (!stos.isEmpty() && stos.peek() == '(') {
						stos.pop();
					} else if (stos.isEmpty()) {
						System.out.println("Brak znaku otwierającego dla: ')' " );
					} else {
						System.out.println("Nieprawidłowe zamknięcie nawiasu: " + stos.peek() + " użyto znaku: " + (char)c);
						try {
							stos.remove(stos.lastIndexOf('(')); // usuwa ze stosu ostatnie wystąpienie (
							}catch (ArrayIndexOutOfBoundsException e) {
								System.out.println("Brak znaku otwierającego dla: ')'");
							}
					}
					break;

				case '[':
					++nb;
					stos.push((char) c);
					break;
				case ']':
					--nb;
					if (!stos.isEmpty() && stos.peek() == '[') {
						stos.pop();
					} else if (stos.isEmpty()) {
						System.out.println("Brak znaku otwierającego dla: ']' " );
					} else {
						System.out.println("Nieprawidłowe zamknięcie nawiasu: " + stos.peek() + " użyto znaku: " + (char)c);
						try {
						stos.remove(stos.lastIndexOf('[')); // usuwa ze stosu ostatnie wystąpienie [
						}catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("Brak znaku otwierającego dla: ']'");
						}
					}
					break;

				case '{':
					++nk;
					stos.push((char) c);
					break;
				case '}':
					--nk;
					if (!stos.isEmpty() && stos.peek() == '{') {
						stos.pop();
					} else if (stos.isEmpty()) {
						System.out.println("Brak znaku otwierającego dla: '}' ");
					} else {
						System.out.println("Nieprawidłowe zamknięcie nawiasu: " + stos.peek() + " użyto znaku: " + (char)c);
						try {
							stos.remove(stos.lastIndexOf('{')); // usuwa ze stosu ostatnie wystąpienie {
							}catch (ArrayIndexOutOfBoundsException e) {
								System.out.println("Brak znaku otwierającego dla: '}'");
							}
					}
					break;

				default:
					break;
				}
			}
			if (stos.empty() && np != 0 && nb != 0 && nk != 0)
				System.out.println("Struktura nawiasowa jest poprawna");
			if (!stos.empty())
				System.out.println("W pliku pozostały nieprawidłowo zamknięte nawiasy: " + stos);

			if (np != 0)
				System.out.println("Brak bilansowania '()'");
			if (nb != 0)
				System.out.println("Brak bilansowania '[]'");
			if (nk != 0)
				System.out.println("Brak bilansowania '{}'");

			System.out.println("\nnp=" + np + ", nb=" + nb + ", nk=" + nk);
		} finally {
			if (ifile != null)
				ifile.close();
		}
		scn.close();
	}
}
