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

  def 'replace muliple same banned word in multiple word sentence'() {
    when:
    String result = sentence_filter.change("The orange coloured bat and orange hat");

    then:
    result == "The -r-ng- coloured bat and -r-ng- hat";
  }

  def 'replace muliple same banned word different cases in multiple word sentence'() {
    when:
    String result = sentence_filter.change("The orange coloured bat and Orange hat");

    then:
    result == "The -r-ng- coloured bat and -r-ng- hat";
  }

  def 'replace two different banned words in multiple word sentence'() {
    when:
    String result = sentence_filter.change("The Red orange is happy");

    then:
    result == "The R-d -r-ng- is happy";
  }

}
