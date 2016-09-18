import spock.lang.*

class SentenceFilterSpec extends Specification {
  BannedWords banned          = Mock();
  ExceptionWords exceptions   = Mock();
  Filter filter               = Mock();

  def sentence_filter = new SentenceFilter(exceptions, banned, filter)

  def '001 replace vowels in multiple word sentence'() {
    given:
    banned.containedIn('orange')          >> true;
    exceptions.doesNotIncludes('orange')  >> true;
    filter.applyTo('orange')              >> '-r-ng-';

    when:
    String result = sentence_filter.change("The orange is happy");

    then:
    result == "The -r-ng- is happy";
  }

  def '002 replace no vowels in multiple word sentence'() {
    given:
    banned.containedIn('covered')         >> true;
    exceptions.doesNotIncludes('covered') >> false;

    when:
    String result = sentence_filter.change("The coloured bat");

    then:
    result == "The coloured bat";
  }

  def '003 replace muliple same banned word in multiple word sentence'() {
    given:
    banned.containedIn('orange')            >> true;
    banned.containedIn('coloured')          >> true;
    exceptions.doesNotIncludes('orange')    >> true;
    exceptions.doesNotIncludes('coloured')  >> false;
    filter.applyTo('orange')                >> '-r-ng-';

    when:
    String result = sentence_filter.change("The orange coloured bat and orange hat");

    then:
    result == "The -r-ng- coloured bat and -r-ng- hat";
  }

  def '004 replace muliple same banned word different cases in multiple word sentence'() {
    given:
    banned.containedIn('Orange')    >> true;
    banned.containedIn('coloured')  >> true;
    banned.containedIn('orange')    >> true;
    exceptions.doesNotIncludes(_)   >>> [true,false,true];
    filter.applyTo(_)               >> '-r-ng-';

    when:
    String result = sentence_filter.change("The orange coloured bat and Orange hat");

    then:
    result == "The -r-ng- coloured bat and -r-ng- hat";
  }

  def '005 replace two different banned words in multiple word sentence'() {
    given:
    banned.containedIn('Red')     >> true;
    banned.containedIn('orange')  >> true;
    exceptions.doesNotIncludes(_) >> true;
    filter.applyTo('orange')      >> '-r-ng-';
    filter.applyTo('Red')         >> 'R-d';

    when:
    String result = sentence_filter.change("The Red orange is happy");

    then:
    result == "The R-d -r-ng- is happy";
  }

  def '006 replace banned word in another word'() {
    given:
    banned.containedIn('Reddit')  >> true;
    banned.containedIn('orange')  >> true;
    exceptions.doesNotIncludes(_) >> true;
    filter.applyTo('orange')      >> '-r-ng-';
    filter.applyTo('Reddit')      >> 'R-dd-t';

    when:
    String result = sentence_filter.change("The Reddit orange is happy");

    then:
    result == "The R-dd-t -r-ng- is happy";
  }

  def '007 replace banned word in non exception word'() {
    given:
    banned.containedIn(_)                 >>> [false,true,true,
                                              true,true,true,
                                              false,false];
    exceptions.doesNotIncludes('Reddit')  >> true;
    exceptions.doesNotIncludes('red')     >> true;
    exceptions.doesNotIncludes('green')   >> true;
    filter.applyTo(_)                     >>> ['R-dd-t','r-d',
                                              'gr--n'];

    when:
    String result = sentence_filter.change("The Reddit coloured red covered green is happy");

    then:
    result == "The R-dd-t coloured r-d covered gr--n is happy";
  }

  def '008 use different set of exception words'() {
    given:
    banned.containedIn(_)         >>>  [true,false,true,true,
                                        true,false,true,true,
                                        false,true,true, true];
    exceptions.doesNotIncludes(_) >> true;
    filter.applyTo(_)             >>> ['-r-ng-', 'fl-r-ng-',
                                      'bl--', 'Bl--t-c', 'r-d', 'r-dd-t', 'gr--n',  'gr--n-ry'];

    when:
    String result = sentence_filter.change("Orange evergreen florAnge blue Bluetac greenbelt red reddit blues green greenary");

    then:
    result == "-r-ng- evergreen fl-r-ng- bl-- Bl--t-c greenbelt r-d r-dd-t blues gr--n gr--n-ry";
  }
}
