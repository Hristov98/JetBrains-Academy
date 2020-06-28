import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int n = scan.nextInt();
        char[] password = new char[n];
        int index = 0;

        for (int i = 0; i < a; i++) {
            password[index++] = (char) (('A' + (i % 26)));
        }

        for (int i = 0; i < b; i++) {
            password[index++] = (char) (('a' + (i % 26)));
        }

        for (int i = 0; i < c; i++) {
            password[index++] = (char) (('0' + (i % 10)));
        }

        while (index < n) {
            password[index++] = (char) (('A' + (index % 26)));
        }

        System.out.println(password);
    }
}
