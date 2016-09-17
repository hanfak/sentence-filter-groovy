import spock.lang.*

class BannedWordsSpec extends Specification {
  def banned = ["red", "blue"];
  def banned_words = new BannedWords(banned);

  def '001 word in one word'() {
    when:
    Boolean result = banned_words.containedIn("red");

    then:
    result == true;
  }

  def '002 word in list of words'() {
    when:
    Boolean result = banned_words.containedIn("Blue");

    then:
    result == true;
  }

  def '003 word not in list of words'() {
    when:
    Boolean result = banned_words.containedIn("green");

    then:
    result == false;
  }

  def '004 part of word in list of words'() {
    when:
    Boolean result = banned_words.containedIn("redbelt");

    then:
    result == true;
  }

  def '005 in middle of word, in list of words'() {
    when:
    Boolean result = banned_words.containedIn("theBluesit");

    then:
    result == true;
  }
}
