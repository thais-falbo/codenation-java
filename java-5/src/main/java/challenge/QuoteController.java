package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class QuoteController {

	@Autowired
	private QuoteService service;

	@GetMapping(value = "/quote", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Quote> getQuote() {
		return new ResponseEntity<>(this.service.getQuote(), HttpStatus.OK);
	}

	@GetMapping(value = "/quote/{actor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Quote> getQuoteByActor(@PathVariable("actor") String actor) {
		return new ResponseEntity<>(this.service.getQuoteByActor(actor), HttpStatus.OK);
	}

}
