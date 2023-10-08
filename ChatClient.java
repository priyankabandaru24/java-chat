import java.io.*;
import java.net.*;

/**
 * ChatClient class connects to a chat server running on localhost at port 1234.
 * It allows the user to send messages to the server and receive replies.
 * The chat continues until the user types "bye".
 */
public class ChatClient {

    public static void main(String[] args) {
        // Try to connect to the server and run the chat
        try (Socket socket = new Socket("localhost", 1234)) {

            System.out.println("Connected to server."); // Confirm connection

            // To read messages sent by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // To send messages to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // To read user input from the console
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String userMessage, serverMessage;

            // Loop to continuously send and receive messages
            while (true) {
                System.out.print("You: "); // Prompt user to type a message
                userMessage = userInput.readLine(); // Read the message from user
                out.println(userMessage); // Send it to the server

                // If the user types "bye", exit the loop
                if (userMessage.equalsIgnoreCase("bye")) break;

                // Read the reply from the server
                serverMessage = in.readLine();
                System.out.println("Server: " + serverMessage); // Print server message
            }

            // Inform that chat has ended
            System.out.println("Chat ended.");

        } catch (IOException e) {
            // Print the error if any issue occurs (like server not found)
            e.printStackTrace();
        }
    }
}
