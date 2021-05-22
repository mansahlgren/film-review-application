package main;

/**
 * Represents search parameters from the browser.
 * 
 * @author Mans
 *
 */

public class SearchParameters {

	private String title;
	private String openingDateFrom;;
	private String openingDateTo;
	private String publicationDateFrom;
	private String publicationDateTo;
	private int offset; // Item offset in the result. For example, 0, 20 or 40 for fetching items 0-20,
						// 20-40 or 40-60 etc.
	private int order = -1;

	@Override
	public String toString() {
		return "SearchParameters [title=" + title + ", openingDateFrom=" + openingDateFrom + ", openingDateTo="
				+ openingDateTo + ", publicationDateFrom=" + publicationDateFrom + ", publicationDateTo="
				+ publicationDateTo + ", offset=" + offset + ", order=" + order + "]";
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpeningDateFrom() {
		return openingDateFrom;
	}

	public void setOpeningDateFrom(String openingDateFrom) {
		this.openingDateFrom = openingDateFrom;
	}

	public String getOpeningDateTo() {
		return openingDateTo;
	}

	public void setOpeningDateTo(String openingDateTo) {
		this.openingDateTo = openingDateTo;
	}

	public String getPublicationDateFrom() {
		return publicationDateFrom;
	}

	public void setPublicationDateFrom(String publicationDateFrom) {
		this.publicationDateFrom = publicationDateFrom;
	}

	public String getPublicationDateTo() {
		return publicationDateTo;
	}

	public void setPublicationDateTo(String publicationDateTo) {
		this.publicationDateTo = publicationDateTo;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
