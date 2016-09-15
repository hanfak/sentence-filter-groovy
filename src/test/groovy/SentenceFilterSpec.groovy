import spock.lang.*

class SentenceFilterSpec extends Specification {
  def sentence_filter = new SentenceFilter()

  def 'replace vowels in multiple word sentence'() {
    when:
    String result = sentence_filter.change("The orange is happy");

    then:
    result == "The -r-ng- is happy";
  }

  def 'replace no vowels in multiple word sentence'() {
    when:
    String result = sentence_filter.change("The coloured bat");

    then:
    result == "The coloured bat";
  }

}
