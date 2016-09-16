class SentenceFilter {
  def change(String sentence) {
    String[] list = sentence.split(" ");
    this.getBannedWords().each {banned_word ->
        list = list.collect {word ->
          this.checkWordContainsBannedWord(word, banned_word) &&  this.checkWordNotInExceptions(word) ? this.replaceVowels(word) : word;
        }
    }
    return list.join(' ')
  }

  private def getExceptionWords() {
    return ["coloured","covered"];
  }

  private def checkWordNotInExceptions(word) {
    !this.getExceptionWords().findAll {it.toLowerCase() == word}
  }

  private def getBannedWords() {
    return ["orange", 'red', 'green']
  }

  private def checkWordContainsBannedWord(word, banned_word) {
    return word.toLowerCase().contains(banned_word)
  }

  private def replaceVowels(String word){
    return word.replaceAll(/(?i)[aeiou]/,"-");
  }
}
