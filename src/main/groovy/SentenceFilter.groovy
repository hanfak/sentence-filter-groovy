class SentenceFilter {
  private ExceptionWords exception_words;
  private String[] banned_words;

  def SentenceFilter(exceptions, banned_words) {
    this.exception_words = exceptions;
    this.banned_words = banned_words;
  }

  def change(String sentence) {
    String[] list = sentence.split(" ");
    list = list.collect { word ->
      this.checkToChangeWord(word) ? this.replaceVowels(word) : word;
    }
    return list.join(' ');
  }

  private def checkWordContainsBannedWord(word) {
    return this.banned_words.find { word.toLowerCase().contains(it) }
  }

  private def checkToChangeWord(word) {
    return this.checkWordContainsBannedWord(word) &&  this.exception_words.doesNotIncludes(word)
  }

  private def replaceVowels(String word){
    return word.replaceAll(/(?i)[aeiou]/,"-");
  }
}
