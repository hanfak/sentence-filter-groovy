import spock.lang.*

class ExceptionWordsSpec extends Specification {
  def exceptions = ["reddit", "blues"];
  def exception_words = new ExceptionWords(exceptions);

  def '001 word in word'() {
    when:
    Boolean result = exception_words.doesNotIncludes("reddit");

    then:
    result == false;
  }

  def '002 word in list of words'() {
    when:
    Boolean result = exception_words.doesNotIncludes("blues");

    then:
    result == false;
  }

  def '003 word not in list of words'() {
    when:
    Boolean result = exception_words.doesNotIncludes("gold");

    then:
    result == true;
  }
}
