class SentenceFilter {
  def change(String sentence) {
    return sentence.replaceAll(/(?i)[aeiou]/,'-');
  }
}
