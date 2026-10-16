package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1_3 {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1235)) {
            System.out.println("Serveur en attente d'un client...");

            try (Socket socket = serverSocket.accept();
                 DataInputStream dis =
                         new DataInputStream(socket.getInputStream());
                 DataOutputStream dos =
                         new DataOutputStream(socket.getOutputStream())) {

                System.out.println("Un client est connecté : "
                        + socket.getInetAddress().getHostAddress());

                while (true) {

                    int code = dis.readInt();
                    System.out.println("Code reçu : " + code);


                    if (code == 0) {
                        System.out.println("Le client a demandé la fin.");
                        break;
                    }


                    int x = dis.readInt();
                    System.out.println("Valeur x reçue : " + x);

                    int resultat;

                    switch (code) {
                        case 1:
                            resultat = x + 5;
                            System.out.println("Addition : " + x + " + 5 = " + resultat);
                            break;

                        case 2:
                            resultat = x - 5;
                            System.out.println("Soustraction : " + x + " - 5 = " + resultat);
                            break;

                        case 3:
                            resultat = x * 5;
                            System.out.println("Multiplication : " + x + " * 5 = " + resultat);
                            break;

                        case 4:
                            if (x == 0) {
                                resultat = Integer.MIN_VALUE;
                                System.out.println("Division par 0 refusée !");
                            } else {
                                resultat = x / 5;
                                System.out.println("Division : " + x + " / 5 = " + resultat);
                            }
                            break;

                        default:
                            resultat = Integer.MIN_VALUE;
                            System.out.println("Code invalide : " + code);
                    }


                    dos.writeInt(resultat);
                    dos.flush();
                }
            }
        }

        System.out.println("Serveur arrêté.");
    }
}