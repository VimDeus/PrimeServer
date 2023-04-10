package primechecker;

import java.io.*;
import java.net.*;

public class PrimeServer {
    public static void main(String[] args) throws IOException {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);) {

                    System.out.println("Client connected");

                    String inputLine = in.readLine();
                    int number = Integer.parseInt(inputLine);
                    boolean isPrime = isPrime(number);

                    out.println(isPrime ? "yes" : "no");
                }
            }
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
