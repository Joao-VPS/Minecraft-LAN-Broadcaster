package com.jaoumvps.minelanbroadcaster;

import com.jaoumvps.minelanbroadcaster.utils.Messager;
import com.jaoumvps.minelanbroadcaster.utils.Pair;

import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Pair<String, String>> servers = new ArrayList<>();

    public static void main(String[] args) {
        broadcaster();
    }

    private static void findServers() {
        Messager.print("Adicionando servidores", 'e');
        servers.clear();

        try {
            String path = System.getProperty("user.dir") + "/" + "servers.txt";
            FileReader serversFile = new FileReader(path);
            Scanner serverLine = new Scanner(serversFile);

            while (serverLine.hasNextLine()) {
                String line = serverLine.nextLine();
                if (line.startsWith("#") || line.length() == 0 || line.startsWith("\uFEFF")) {
                    Messager.print("Pulada linha " + line, 'e');
                } else {
                    String[] params = line.split("; ");
                    if (params.length == 2) {
                        servers.add(new Pair<>(params[0], params[1]));
                    } else {
                        Messager.print("Erro na linha " + line, 'c');
                        Messager.print("Parâmetros incorretos.", 'c');
                    }
                }
            }

            Messager.print("Servidores adicionados a lista de broadcast com sucesso.", 'a');
        } catch (Exception e) {
            System.err.println("Falha ao procurar servidores no arquivo servers.txt");
            System.err.println(e.getMessage());
        }
    }

    private static void broadcaster() {
        Messager.print("Iniciando broadcaster...", 'a');
        InetAddress broadcastAddress;

        try {
            broadcastAddress = InetAddress.getByName("255.255.255.255");
        } catch (Exception e) {
            Messager.print("Erro: endereço de broadcast inválido!", '4');
            return;
        }
        int port = 4445;

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);

            while (true) {
                findServers();

                for (Pair server : servers) {
                    String message = "[MOTD]" + server.getKey() + "[/MOTD][AD]" + server.getValue() + "[/AD]";
                    byte[] sendData = message.getBytes();

                    DatagramPacket packet = new DatagramPacket(sendData, sendData.length, broadcastAddress, port);
                    socket.send(packet);

                    Messager.print("Pacote" + message + "enviado com sucesso", 'a');
                }
                Thread.sleep(1500);
            }
        } catch (Exception e) {
            Messager.print("Erro ao enviar mensagem:", '4');
            System.err.println(e.getMessage());
        }
    }
}
