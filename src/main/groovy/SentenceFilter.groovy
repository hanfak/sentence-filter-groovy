class SentenceFilter {
  def change(String sentence) {
    String[] list = sentence.split(" ");
    String[] exceptions = ["coloured","covered"];
    this.getBannedWords().each {banned_word ->
        list = list.collect {word ->
          word.toLowerCase().contains(banned_word) &&  !exceptions.findAll {it.toLowerCase() == word} ? this.replaceVowels(word) : word;
        }
    }
    return list.join(' ')
  }

  private def getBannedWords() {
    return ["orange", 'red', 'green']
  }

  private def replaceVowels(String word){
    return word.replaceAll(/(?i)[aeiou]/,"-");
  }
}
