class SentenceFilter {
  private def exception_words;
  private def banned_words;

  def SentenceFilter(exceptions, banned_words) {
    this.exception_words= exceptions;
    this.banned_words = banned_words;
  }

  def change(String sentence) {
    String[] list = sentence.split(" ");
      list = list.collect {word ->
        this.checkWordContainsBannedWord(word) &&  this.checkWordNotAnException(word) ? this.replaceVowels(word) : word;
      }
    return list.join(' ');
  }

  private def checkWordNotAnException(word) {
    !this.exception_words.findAll { it.toLowerCase() == word }
  }

  private def checkWordContainsBannedWord(word) {
    return this.banned_words.findAll { word.toLowerCase().contains(it) }
  }

  private def replaceVowels(String word){
    return word.replaceAll(/(?i)[aeiou]/,"-");
  }
}
