class SentenceFilter {
  def change(String sentence) {
    String[] banned_words = ["orange", 'red'];
    String[] list = sentence.split(" ")
    banned_words.each {banned_word ->
        list = list.collect {word ->
          word.toLowerCase() == banned_word ? this.replaceVowels(word) : word;
        }
    }
    return list.join(' ')
  }

  private def replaceVowels(String word){
    return word.replaceAll(/(?i)[aeiou]/,"-");
  }
}
