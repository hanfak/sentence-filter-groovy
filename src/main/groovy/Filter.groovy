class Filter {
  def applyTo(word) {
    return word.replaceAll(/(?i)[aeiou]/,"-");
  }
}
