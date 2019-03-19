//package UDP;

import java.net.*;
import java.io.*;

public class UDPCliente{

public static void main(String[] args) throws SocketException, IOException{
	
	//cria um socket udp
	DatagramSocket s = new DatagramSocket();
	System.out.println("* Socket criado na porta: " + s.getLocalPort());
	byte[] m = args[0].getBytes(); //transforma arg em bytes

	InetAddress serv = InetAddress.getByName(args[1]);
	int porta = 6789;
	DatagramPacket req = new DatagramPacket(m, args[0].length(), serv, porta);

	//envia datagrama contendo a mensagem m
	s.send(req);

	byte[] buffer = new byte[1000];
	DatagramPacket resp = new DatagramPacket(buffer, buffer.length);
	s.setSoTimeout(10000); //timeout em ms

	//recebe resposta do servidor - fica em wait ateh chegada
	s.receive(resp);
	System.out.println("* Respost do servidor:" + new String(resp.getData()));

	//fecha socket
	s.close();
	}
}

