class ExceptionWords {
  private String[] exception_words;

  def ExceptionWords(exceptions_words) {
    this.exception_words = exceptions_words;
  }

  def doesNotIncludes(word) {
    return !this.exception_words.contains(word.toLowerCase());
  }
}
