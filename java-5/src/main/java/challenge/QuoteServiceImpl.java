package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository repository;

	@Override
	public Quote getQuote() {
		return this.repository.findAll().stream().findAny().get();
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		return this.repository.findByActor(actor).stream().findAny().get();
	}
}
