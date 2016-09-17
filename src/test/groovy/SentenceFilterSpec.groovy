import spock.lang.*

class SentenceFilterSpec extends Specification {
  def banned_words = ["orange", 'red', 'green'];
  ExceptionWords exceptions = Mock();
  def sentence_filter = new SentenceFilter(exceptions, banned_words)

  def '001 replace vowels in multiple word sentence'() {
    given:
    exceptions.doesNotIncludes('orange') >> true;

    when:
    String result = sentence_filter.change("The orange is happy");

    then:
    result == "The -r-ng- is happy";
  }

  def '002 replace no vowels in multiple word sentence'() {
    when:
    String result = sentence_filter.change("The coloured bat");

    then:
    result == "The coloured bat";
  }

  def '003 replace muliple same banned word in multiple word sentence'() {
    given:
    exceptions.doesNotIncludes('orange') >> true;

    when:
    String result = sentence_filter.change("The orange coloured bat and orange hat");

    then:
    result == "The -r-ng- coloured bat and -r-ng- hat";
  }

  def '004 replace muliple same banned word different cases in multiple word sentence'() {
    given:
    exceptions.doesNotIncludes('orange') >> true;
    exceptions.doesNotIncludes('Orange') >> true;

    when:
    String result = sentence_filter.change("The orange coloured bat and Orange hat");

    then:
    result == "The -r-ng- coloured bat and -r-ng- hat";
  }

  def '005 replace two different banned words in multiple word sentence'() {
    given:
    exceptions.doesNotIncludes('Red') >> true;
    exceptions.doesNotIncludes('orange') >> true;

    when:
    String result = sentence_filter.change("The Red orange is happy");

    then:
    result == "The R-d -r-ng- is happy";
  }

  def '006 replace banned word in another word'() {
    given:
    exceptions.doesNotIncludes('Reddit') >> true;
    exceptions.doesNotIncludes('orange') >> true;

    when:
    String result = sentence_filter.change("The Reddit orange is happy");

    then:
    result == "The R-dd-t -r-ng- is happy";
  }

  def '007 replace banned word in non exception word'() {
    given:
    exceptions.doesNotIncludes('Reddit') >> true;
    exceptions.doesNotIncludes('green') >> true;
    exceptions.doesNotIncludes('red') >> true;

    when:
    String result = sentence_filter.change("The Reddit coloured red covered green is happy");

    then:
    result == "The R-dd-t coloured r-d covered gr--n is happy";
  }

  def '008 use different set of exception words'() {
    given:
    def banned_words = ["orange", 'red', 'green', 'blue'];
    def sentence_filter = new SentenceFilter(exceptions, banned_words)
    exceptions.doesNotIncludes('Orange') >> true;
    exceptions.doesNotIncludes('florAnge') >> true;
    exceptions.doesNotIncludes('blue') >> true;
    exceptions.doesNotIncludes('Bluetac') >> true;
    exceptions.doesNotIncludes('red') >> true;
    exceptions.doesNotIncludes('reddit') >> true;
    exceptions.doesNotIncludes('green') >> true;
    exceptions.doesNotIncludes('greenary') >> true;

    when:
    String result = sentence_filter.change("Orange evergreen florAnge blue Bluetac greenbelt red reddit blues green  greenary");

    then:
    result == "-r-ng- evergreen fl-r-ng- bl-- Bl--t-c greenbelt r-d r-dd-t blues gr--n  gr--n-ry";
  }
}
