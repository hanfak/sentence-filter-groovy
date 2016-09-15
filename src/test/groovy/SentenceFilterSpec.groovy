import spock.lang.*

class SentenceFilterSpec extends Specification {
  def sentence_filter = new SentenceFilter()

  def 'replace 1 vowel in 1 word sentence'() {
    when:
    String result = sentence_filter.change("red")

    then:
    result == 'r-d'
  }

  def 'replace 2 same vowels in 1 word sentence'() {
    when:
    String result = sentence_filter.change("green")

    then:
    result == 'gr--n'
  }

  def 'replace 3 different vowels in 1 word sentence'() {
    when:
    String result = sentence_filter.change("orange")

    then:
    result == '-r-ng-'
  }

  def 'replace n different vowels in 1 word sentence'() {
    when:
    String result = sentence_filter.change(word)

    then:
    result == expected

    where:
    word      | expected
    'red'     | 'r-d'
    'orange'  | '-r-ng-'
    'green'   | 'gr--n'
    'indigo'  | '-nd-g-'
    'purple'  | 'p-rpl-'
  }

  // test rest of vowels i and u
  // test no vowels in word

}
