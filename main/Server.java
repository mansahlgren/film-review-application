package main;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Listens for POST requests from the browser.
 * 
 * @author Mans
 *
 */

public class Server {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
			HttpHandler handler = new MyHttpHandler(mapper);
			HttpContext context = server.createContext("/");
			context.setHandler(handler);
			server.start();
			System.out.println("Server started on port 8001");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
