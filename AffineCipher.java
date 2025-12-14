// محمد خالد الدحيش خلاوي احمد

import java.util.Scanner;

public class AffineCipher {

    public static int gcd(int mo1, int mo2) {
        if (mo2 == 0) return mo1;
        return gcd(mo2, mo1 % mo2);
    }

    public static int modInverse(int mo1, int mo2) {
        mo1 = mo1 % mo2;
        for (int x = 1; x < mo2; x++) {
            if ((mo1 * x) % mo2 == 1)
                return x; 
        }
        return -1; 
    }

    public static String encrypt(String text, int mo1, int mo2) {

        String result = "";
        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {

                int x = ch - 'A';
                int encrypted = (mo1 * x + mo2) % 26;
                result += (char) (encrypted + 'A');

            } else {
                result += ch; 
            }
        }
        return result;
    }

    public static String decrypt(String cipher, int mo1, int mo2) {

        cipher = cipher.toUpperCase();
        String result = "";

        int a_inv = modInverse(mo1, 26);
        if (a_inv == -1) {
            return "No modular inverse exists!";
        }

        for (int i = 0; i < cipher.length(); i++) {
            char ch = cipher.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {

                int y = ch - 'A';

                int decrypted = a_inv * (y - mo2 + 26) % 26;

                result += (char) (decrypted + 'A');

            } else {
                result += ch;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of a: ");
        int a = sc.nextInt();

        if (gcd(a, 26) != 1) {
            System.out.println("Error: a is NOT coprime with 26! Program stopped.");
            return;
        }
        System.out.print("Enter value of b: ");
        int b = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Encrypt or Decrypt (E/D)? ");
        char choice = sc.next().toUpperCase().charAt(0);

        if (choice == 'E') {
            System.out.println("Encrypted: " + encrypt(text, a, b));
        } else if (choice == 'D') {
            System.out.println("Decrypted: " + decrypt(text, a, b));
        } else {
            System.out.println("Invalid choice!");
        }
        sc.close();
     }
  }