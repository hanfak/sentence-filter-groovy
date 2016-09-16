import spock.lang.*

class ExceptionWordsSpec extends Specification {
  def exceptions = ["reddit", "blues"];
  def exception_words = new ExceptionWords(exceptions);

  def '001'() {
    when:
    Boolean result = exception_words.doesNotIncludes("reddit");

    then:
    result == false;
  }

  def '002'() {
    when:
    Boolean result = exception_words.doesNotIncludes("blues");

    then:
    result == false;
  }

  def '002'() {
    when:
    Boolean result = exception_words.doesNotIncludes("gold");

    then:
    result == true;
  }
}
