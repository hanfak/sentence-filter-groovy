import spock.lang.*

class SentenceFilterSpec extends Specification {
  def sentence_filter = new SentenceFilter()

  def 'working'() {
      when:
      String res = sentence_filter.check()

      then:
      res == 'hello it is working!'
  }
}
