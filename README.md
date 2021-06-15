# film-review-application

Application to search for film reviews using an external API belonging to NYT. The search can be preformed using several different parameters, and the application can show information about an arbitrary number of movies.

The program consists of a website, and a java server. The website is used to input the values of the parameters, and to show the search result. The java server
receives parameters from the website, communicates with the NYT API and parses the result.

**Run instructions**

Maven project. Run the main method in Server.java. Then open Movie-review-searcher.html (tested in Chrome) and start searching.
