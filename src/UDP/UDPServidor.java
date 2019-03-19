package UDP;

import java.net.*;
import java.io.*;

public class UDPServidor {

    public static void main(String[] args) throws SocketException, IOException {

        char[] gabarito = new char[10];
        int acerto = 0, erro = 0;

        gabarito[0] = 'V';
        gabarito[1] = 'V';
        gabarito[2] = 'F';
        gabarito[3] = 'V';
        gabarito[4] = 'V';
        gabarito[5] = 'F';
        gabarito[6] = 'F';
        gabarito[7] = 'V';
        gabarito[8] = 'F';
        gabarito[9] = 'F';

        //cria um socket udp
        DatagramSocket s = new DatagramSocket(6789);
        byte[] buffer = new byte[1000];
        System.out.println(" *** Seervidor aguardando request");

        //cria datagrama para receber request do cliente
        DatagramPacket r = new DatagramPacket(buffer, buffer.length);
        s.receive(r);
        System.out.println(" *** Request recebido de: " + r.getAddress() + " msg: " + new String(r.getData()));

        //StringBuilder build = new StringBuilder(new String(r.getData()));
        //String aux = String.valueOf(build.reverse());
        //System.out.println(aux);
        //r.setData(aux.getBytes());
        String resposta = new String(r.getData());
        System.out.println("resposta: " + resposta + "\n");

        String[] aux = resposta.split(";");
        System.out.println("aux[2]:" + aux[2]);
        //String teste = "FF";
        String compara = aux[2];
        for (int i = 0; i < Integer.parseInt(aux[1]); i++) {
            if (compara.charAt(i) == gabarito[i]) {
                acerto++;
                System.out.println("acerto" + i);
            } else {
                erro++;
                System.out.println("errou" + i);
                
            }
        }

        String fim = String.valueOf("questao: " + aux[0]) + ";" + "Acertos: " +  String.valueOf(acerto) + ";" + "Erros: " +  String.valueOf(erro);
        System.out.println("fim:" + fim);
        r.setData(fim.getBytes());

        //envia resposta
        DatagramPacket resp = new DatagramPacket(r.getData(), r.getLength(), r.getAddress(), r.getPort());
        //System.out.println(new String(resp.getData()));
        s.send(resp);

        s.close();

    }

}
