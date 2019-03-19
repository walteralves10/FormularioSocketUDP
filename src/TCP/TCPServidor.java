package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServidor{
    
    //public final static String FILE_TO_SEND = "/home/rezende/Documentos/faculdade/9 periodo/trabalho final de curso 1/5 - Modelo de AnteProjeto.pdf";

	public static void main(String[] args){
		try{
			int porta = 6789;//porta do serviçp
			if(args.length > 0) porta = Integer.parseInt(args[0]);
			ServerSocket escuta = new ServerSocket(porta);
			System.out.println("*** Servidor ***");
			System.out.println("*** Porta de escuta (listen): " + porta);

			while(true){
				//accept: bloqueia servidor até que chegue um
				//pedido de conexao de um cliente
				Socket cliente = escuta.accept();
				System.out.println("*** Conexao aceita de (remoto): " + cliente.getRemoteSocketAddress());
				//quando chega, cria nova trread para atender o 
				//cliente passando o socket retornado por accept
				//Conexao c = new Conexao(cliente);
                                DataInputStream ent = new DataInputStream(cliente.getInputStream());
                                String recebe = ent.readUTF();
                                System.out.println(recebe);
                                
                               //sai do servidor e vai ate o cliente 
                                DataOutputStream sai = new DataOutputStream(cliente.getOutputStream());
                                sai.writeUTF(recebe);
			}
		}catch(IOException e){
			System.out.println("Erro na escuta: " + e.getMessage());
		}
	}

}