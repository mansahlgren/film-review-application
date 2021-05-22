package main;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Parses JSON review results from NYT into instances of the MovieReview class,
 * and parses JSON search parameters from the browser into an instance of the
 * SearchParameters class.
 * 
 * @author Mans
 *
 */

public class Parser {
	private ObjectMapper mapper;

	public Parser(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public List<MovieReview> parseReviewsResult(String json) {

		List<MovieReview> reviews = new ArrayList<>();
		MovieReview review;

		try {
			JsonNode resultsNode = mapper.readTree(json).get("results");
			// System.out.println("Length: " + resultsNode.size());
			for (int i = 0; i < resultsNode.size(); i++) {
				review = new MovieReview();
				review.setDisplayTitle(resultsNode.get(i).get("display_title").textValue());
				review.setCriticsPick(resultsNode.get(i).get("critics_pick").asBoolean());
				review.setByline(resultsNode.get(i).get("byline").textValue());
				review.setHeadline(resultsNode.get(i).get("headline").textValue());
				review.setShortSummary(resultsNode.get(i).get("summary_short").textValue());
				review.setPublicationDate(resultsNode.get(i).get("publication_date").textValue());
				review.setOpeningDate(resultsNode.get(i).get("opening_date").textValue());
				review.setLinktext(resultsNode.get(i).get("link").get("suggested_link_text").textValue());
				review.setUrlLink(resultsNode.get(i).get("link").get("url").textValue());
				reviews.add(review);
				// System.out.println("Review: " + review);
			}

		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		return reviews;
	}

	public SearchParameters parseSearchParameters(String json) {
		try {

			JsonNode searchParamsNode = mapper.readTree(json);
			SearchParameters searchParams = new SearchParameters();
			searchParams.setTitle(searchParamsNode.get("title").asText());
			searchParams.setOpeningDateFrom(searchParamsNode.get("openingDateFrom").asText());
			searchParams.setOpeningDateTo(searchParamsNode.get("openingDateTo").asText());
			searchParams.setPublicationDateFrom(searchParamsNode.get("publicationDateFrom").asText());
			searchParams.setPublicationDateTo(searchParamsNode.get("publicationDateTo").asText());
			searchParams.setOffset(searchParamsNode.get("offset").asInt());
			// System.out.println("Search params: " + searchParams);
			return searchParams;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
