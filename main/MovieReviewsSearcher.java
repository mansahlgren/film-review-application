package main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Extracts the search parameters from a SearchParameters object, creates the
 * search string and the then searches for movies reviews.
 * 
 * @author Mans
 *
 */
public class MovieReviewsSearcher {
	private SearchStringBuilder ssb = new SearchStringBuilder();
	private Parser parser;
	private HttpClient httpClient = HttpClient.newHttpClient();

	public MovieReviewsSearcher(ObjectMapper mapper) {
		this.parser = new Parser(mapper);
	}

	private String createSearchString(SearchParameters searchParams) {

		String title = searchParams.getTitle();
		String openingDateFrom = searchParams.getOpeningDateFrom();
		String openingDateTo = searchParams.getOpeningDateTo();
		String publicationDateFrom = searchParams.getPublicationDateFrom();
		String publicationDateTo = searchParams.getPublicationDateTo();
		int offset = searchParams.getOffset();

		ssb.setBaseUrl();
		ssb.setOffset(offset);

		if (title != "") {
			ssb.setFreeText(title);
		}

		if (openingDateFrom != "" && openingDateTo != "") {
			ssb.setOpeningDateRange(openingDateFrom + ":" + openingDateTo);
		}

		if (publicationDateFrom != "" && publicationDateTo != "") {
			ssb.setPublicationDateRange(publicationDateFrom + ":" + publicationDateTo);
		}

		ssb.setAPIKey();
		String searchString = ssb.getSearchString();
		ssb.resetBuilder();
		return searchString;

	}

	/*
	 * Search for movie reviews, then return the result in the form of a list of
	 * movie reviews.
	 */

	public List<MovieReview> search(SearchParameters searchParams) {
		String searchString = createSearchString(searchParams);
		// System.out.println("Search string: " + searchString);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(searchString)).GET()
				.header("accept", "application/json").build();

		try {
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
			// System.out.println("Response body: " + response.body() + "\n");
			return parser.parseReviewsResult(response.body());

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;

	}

}
