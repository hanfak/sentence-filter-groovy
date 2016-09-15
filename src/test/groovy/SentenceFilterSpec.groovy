import spock.lang.*

class SentenceFilterSpec extends Specification {
  def sentence_filter = new SentenceFilter()

  def 'replace n different vowels in 1 word sentence'() {
    when:
    String result = sentence_filter.change(word);

    then:
    result == expected;

    where:
    word      | expected
    'red'     | 'r-d'
    'orange'  | '-r-ng-'
    'green'   | 'gr--n'
    'indigo'  | '-nd-g-'
    'purple'  | 'p-rpl-'
  }

  def 'no replacement when no vowels in word sentence'() {
    when:
    String result = sentence_filter.change('rhythm');

    then:
    result == 'rhythm';
  }

  def 'replace vowels that are case insensitive in 1 word sentence'() {
    when:
    String result = sentence_filter.change('OrAnge');

    then:
    result == '-r-ng-';
  }

}
