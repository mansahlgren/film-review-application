package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles HTTP POST requests from the browser. Extracts the search information,
 * searches for movie reviews from NYT and sends the result back to the browser.
 * 
 * @author Mans
 *
 */

public class MyHttpHandler implements HttpHandler {
	private MovieReviewsSearcher searcher;
	private Parser parser;
	ObjectMapper mapper;

	public MyHttpHandler(ObjectMapper mapper) {
		this.mapper = mapper;
		this.parser = new Parser(mapper);
		this.searcher = new MovieReviewsSearcher(mapper);
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		Headers headers = httpExchange.getResponseHeaders();
		headers.add("Access-Control-Allow-Origin", "*"); // Allow all origins.

		// Handle CORS.
		if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
			headers.add("Access-Control-Allow-Headers", "Content-Type"); // Allow clients to pass in content-type
			headers.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE"); // Allowed methods.
			httpExchange.sendResponseHeaders(204, -1);
			return;
		}

		InputStream is = httpExchange.getRequestBody();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		StringBuilder searchParamsBuilder = new StringBuilder();
		String line = br.readLine();

		// Read the search parameters.
		while (line != null) {
			searchParamsBuilder.append(line);
			line = br.readLine();
		}

		// System.out.println(searchParamsBuilder.toString());

		// Get the search parameters and perform the search.
		SearchParameters searchParams = parser.parseSearchParameters(searchParamsBuilder.toString());
		List<MovieReview> reviews = searcher.search(searchParams);

		// Serialize the list, then send the result to the client.
		String result = mapper.writeValueAsString(reviews);
		// System.out.println("\nSerialized review result: " + result + "\n");
		httpExchange.getResponseHeaders().set("Content-Type", "application/json");
		httpExchange.sendResponseHeaders(200, result.getBytes(StandardCharsets.UTF_8).length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(result.getBytes(StandardCharsets.UTF_8));
		os.close();

		httpExchange.close();
	}
}
