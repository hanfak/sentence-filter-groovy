class SentenceFilter {
  def change(String sentence) {
    String banned_word = "orange"
    String[] list = sentence.split(" ").collect {
      it == banned_word ? this.replaceVowels(it) : it;
    }
    return list.join(' ')
  }

  private def replaceVowels(String word){
    return word.replaceAll(/(?i)[aeiou]/,"-");
  }
}
