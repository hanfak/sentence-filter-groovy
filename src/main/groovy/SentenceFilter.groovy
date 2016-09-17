class SentenceFilter {
  private ExceptionWords exception_words;
  private BannedWords banned_words;

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

  private def checkToChangeWord(word) {
    return this.banned_words.containedIn(word) &&  this.exception_words.doesNotIncludes(word)
  }

  private def replaceVowels(String word){
    return word.replaceAll(/(?i)[aeiou]/,"-");
  }
}
