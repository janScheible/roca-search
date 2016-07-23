# ROCA Search

This is a small prototype of implementing a [ROCA style web application](http://roca-style.org/) with Spring Boot and Thymeleaf.

A requirement is to store the search requests in the database in order to allow analysis of them later.

The application even works with JavaScript disabled. When JavaScript is available Markdown is used for rendering the URLs.
But only if JavaScript and HTML5 History API is available AJAX is used to load the search result.

## Fulfillment of ROCA recommendations 

### Server

| Recommendation | Fulfillment |
| --- | --- |
| #REST | yes |
| #APPLICATION-LOGIC | yes |
| #HTTP | yes	 |
| #LINK | yes |
| #NON-BROWSER | I guess yes... ;-) |
| #SHOULD_FORMATS | not implemented |
| #AUTH | not implemented |
| #COOKIES | yes |
| #SESSION | yes |
| #BROWSER-CONTROLS | yes |

### Client

| Recommendation | Fulfillment |
| --- | --- |
| #POSH | yes |
| #ACCESSIBILITY | I guess yes... |
| #IDIOMATIC-CSS | yes |
| #UNOBTRUSIVE-JAVASCRIPT | yes, works even without JavaScript |
| #NO-DUPLICATION | yes |
| #KNOW-STRUCTURE | yes |
| #STATIC-ASSETS | yes, Typescript is compiled at build time |
| #HISTORYAPI | yes |
