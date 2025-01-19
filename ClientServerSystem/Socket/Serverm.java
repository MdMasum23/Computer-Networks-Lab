import java.io.*;
import java.net.*;

public class Serverm {
    public static void main(String[] args) {
        try {
            // Start the server on port 1234
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is running and waiting for a connection...");

            // Accept a client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Set up input and output streams
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverResponse;

            // Communication loop
            while (true) {
                // Read message from the client
                clientMessage = dataInputStream.readUTF();
                System.out.println("Client: " + clientMessage);

                // Check for exit command
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }

                // Send response to the client
                System.out.print("Server: ");
                serverResponse = serverInput.readLine();
                dataOutputStream.writeUTF(serverResponse);
                dataOutputStream.flush();
            }

            // Close resources
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

/*Run:

javac Serverm.java Clientm.java
java Serverm
java Clientm
*/