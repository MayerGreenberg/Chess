package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {
	private Socket socket;
	private boolean server;
	private ServerSocket listener;

	public Network() throws IOException
	{
		server = true;
		InetAddress localhost = InetAddress.getLocalHost(); 
		System.out.println("Local IP: " + localhost.getHostAddress());
		
		listener= new ServerSocket(9090);
		socket = listener.accept();
	}
	public Network(String adr) throws UnknownHostException, IOException
	{
		server = false;
		socket = new Socket(adr, 9090);
	}
	public void sendServerData(int fr, int fc, int tr, int tc) throws IOException
	{
		PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);
            out.println(fr + " " + fc + " " + tr + " " + tc);
	}
	public int[] getClientData() throws IOException
	{
		BufferedReader input =
	            new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String sin[] = input.readLine().split(" ");
		
		int[] ins = new int[4];
		for (int i = 0; i < 4; i++)
			ins[i] = Integer.parseInt(sin[i]);
		return ins;
	}
	
	public void close() throws IOException
	{
		socket.close();
	}
	
	public boolean isServer()
	{
		return server;
	}
}