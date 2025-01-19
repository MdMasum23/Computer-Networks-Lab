import java.io.*;
import java.net.*;

public class Clientm {
    public static void main(String[] args) {
        try {
            // Connect to the server on localhost at port 1234
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to the server!");

            // Set up input and output streams
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverResponse;

            // Communication loop
            while (true) {
                // Send message to the server
                System.out.print("Client: ");
                clientMessage = clientInput.readLine();
                dataOutputStream.writeUTF(clientMessage);
                dataOutputStream.flush();

                // Check for exit command
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnected from the server.");
                    break;
                }

                // Read response from the server
                serverResponse = dataInputStream.readUTF();
                System.out.println("Server: " + serverResponse);
            }

            // Close resources
            socket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
