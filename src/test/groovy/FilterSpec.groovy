import spock.lang.*

class FilterSpec extends Specification {
  def filter = new Filter()

  def 'replace n different vowels in 1 word sentence'() {
    when:
    String result = filter.applyTo(word);

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
    String result = filter.applyTo('rhythm');

    then:
    result == 'rhythm';
  }

  def 'replace vowels that are case insensitive in 1 word sentence'() {
    when:
    String result = filter.applyTo('OrAnGe');

    then:
    result == '-r-nG-';
  }

}
