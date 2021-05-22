package main;

/**
 * Creates a search string used for searching for movie reviews.
 * 
 * @author Mans
 *
 */
public class SearchStringBuilder {
	private final static String BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?";
	private final static String API_KEY = "7iJVSlU47Ju5zElJnBiG7GWAG1WtQM68";
	private StringBuilder sb = new StringBuilder();

	public void resetBuilder() {
		sb.setLength(0);
	}

	public String getSearchString() {
		return sb.toString();
	}

	public void setBaseUrl() {
		sb.append(BASE_URL);
	}

	public void setFreeText(String freeText) {
		sb.append("&query=");
		String words[] = freeText.split(" ");

		// Add %20 for every space.
		// Don't add %20 at the end.
		int i;
		for (i = 0; i < words.length - 1; i++) {
			sb.append(words[i]);
			sb.append("%20");
		}
		sb.append(words[i]);
	}

	public void setAPIKey() {
		sb.append("&api-key=");
		sb.append(API_KEY);
	}

	public void setOpeningDateRange(String dateRange) {
		sb.append("&opening-date=");
		sb.append(dateRange);
	}

	public void setPublicationDateRange(String publicationDateRange) {
		sb.append("&publication-date=");
		sb.append(publicationDateRange);
	}

	public void setOffset(int offset) {
		sb.append("&offset=");
		sb.append(offset);
	}

}
