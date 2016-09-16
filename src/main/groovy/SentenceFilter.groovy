class SentenceFilter {
  private def exceptions;
  private def banned_words;

  def SentenceFilter(exceptions, banned_words) {
    this.exceptions = exceptions;
    this.banned_words = banned_words;
  }

  def change(String sentence) {
    String[] list = sentence.split(" ");
    this.getBannedWords().each {banned_word ->
        list = list.collect {word ->
          this.checkWordContainsBannedWord(word, banned_word) &&  this.checkWordNotInExceptions(word) ? this.replaceVowels(word) : word;
        }
    }
    return list.join(' ');
  }

  private def getExceptionWords() {
    return this.exceptions;
  }

  private def checkWordNotInExceptions(word) {
    !this.getExceptionWords().findAll {it.toLowerCase() == word}
  }

  private def getBannedWords() {
    return this.banned_words;
  }

  private def checkWordContainsBannedWord(word, banned_word) {
    return word.toLowerCase().contains(banned_word)
  }

  private def replaceVowels(String word){
    return word.replaceAll(/(?i)[aeiou]/,"-");
  }
}
