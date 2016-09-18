class SentenceFilter {
  private ExceptionWords exception_words;
  private BannedWords banned_words;
  private Filter  filter;

  def SentenceFilter(exceptions, banned_words, filter) {
    this.exception_words = exceptions;
    this.banned_words = banned_words;
    this.filter = filter;
  }

  def change(String sentence) {
    if(sentence.trim().isEmpty()) { return ''; }

    String[] list = sentence.split(" ");
    list = list.collect { word ->
      this.checkToChangeWord(word) ? this.filter.applyTo(word) : word;
    }
    return list.join(' ');
  }

  private def checkToChangeWord(word) {
    return this.banned_words.containedIn(word) &&  this.exception_words.doesNotIncludes(word)
  }
}
