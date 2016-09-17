class BannedWords {
  private String[] banned_words;

  def BannedWords(banned_words) {
    this.banned_words = banned_words;
  }

  def containedIn(word) {
    return !!this.banned_words.find { word.toLowerCase().contains(it) }
  }
}
