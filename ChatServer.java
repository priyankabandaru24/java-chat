import java.io.*;
import java.net.*;

/**
 * ChatServer class creates a server that listens on port 1234.
 * It accepts a client connection and allows two-way chatting.
 * The chat continues until the client types "bye".
 */
public class ChatServer {

    public static void main(String[] args) {
        // Try to start the server and handle the chat session
        try (ServerSocket serverSocket = new ServerSocket(1234)) {

            System.out.println("Server started. Waiting for client...");

            // Accept the incoming client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // To read messages sent by the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // To send messages to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // To read messages typed by the server user (you)
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;

            // Loop to handle messaging
            while (true) {
                // Read message from client
                clientMessage = in.readLine();

                // If client says "bye", end the chat
                if (clientMessage.equalsIgnoreCase("bye")) break;

                // Print client's message
                System.out.println("Client: " + clientMessage);

                // Prompt server user to reply
                System.out.print("You: ");
                serverMessage = serverInput.readLine();

                // Send server's reply to the client
                out.println(serverMessage);
            }

            // Close the connection when chat ends
            clientSocket.close();
            System.out.println("Chat ended.");

        } catch (IOException e) {
            // Print any errors if something goes wrong
            e.printStackTrace();
        }
    }
}
