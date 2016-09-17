import spock.lang.*

class BannedWordsSpec extends Specification {
  def banned = ["red", "blue"];
  def banned_words = new BannedWords(banned);

  def '001'() {
    when:
    Boolean result = banned_words.containedIn("red");

    then:
    result == true;
  }

  def '002'() {
    when:
    Boolean result = banned_words.containedIn("Blue");

    then:
    result == true;
  }

  def '003'() {
    when:
    Boolean result = banned_words.containedIn("green");

    then:
    result == false;
  }

  def '004'() {
    when:
    Boolean result = banned_words.containedIn("redbelt");

    then:
    result == true;
  }

  def '005'() {
    when:
    Boolean result = banned_words.containedIn("theBluesit");

    then:
    result == true;
  }
}
