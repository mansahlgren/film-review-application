package main;

/**
 * Holds the result of the movie review search using NYT API.
 * 
 * @author Mans
 *
 */

public class MovieReview {

	private String displayTitle;
	private boolean criticsPick;
	private String byline;
	private String headline;
	private String shortSummary;
	private String publicationDate;
	private String openingDate;
	private String urlLink;
	private String linktext;

	public String getDisplayTitle() {
		return displayTitle;
	}

	public boolean isCriticsPick() {
		return criticsPick;
	}

	public String getByline() {
		return byline;
	}

	public String getHeadline() {
		return headline;
	}

	public String getShortSummary() {
		return shortSummary;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public String getLinktext() {
		return linktext;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	public void setLinktext(String linkText) {
		this.linktext = linkText;
	}

	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}

	public void setCriticsPick(boolean criticsPick) {
		this.criticsPick = criticsPick;
	}

	public void setByline(String byline) {
		this.byline = byline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public void setShortSummary(String shortSummary) {
		this.shortSummary = shortSummary;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}

	@Override
	public String toString() {
		return "MovieReview [displayTitle=" + displayTitle + ", criticsPick=" + criticsPick + ", byline=" + byline
				+ ", headline=" + headline + ", shortSummary=" + shortSummary + ", publicationDate=" + publicationDate
				+ ", openingDate=" + openingDate + ", urlLink=" + urlLink + ", linktext=" + linktext + "]";
	}

}
