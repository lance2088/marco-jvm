import helpers.MarcoSpecification
import marco.lang.exception.ContractViolation
import marco.lang.exception.BindingError
import marco.lang.MarcoNil
import marco.lang.MarcoNumber
import marco.lang.MarcoString
import marco.lang.MarcoSymbol
import marco.lang.exception.TypeError

class Defines extends MarcoSpecification {
    def "defining numbers"() {
        when:
        eval(/ (def x 1) /)

        then:
        lookUp("x") == new MarcoNumber(1)
    }

    def "defining strings"() {
        when:
        eval(/ (def a_value "string value") /)

        then:
        lookUp("a_value") == new MarcoString("string value")
    }

    def "renaming def"() {
        when:
        eval(/ (def define def) /)
        eval(/ (define x 1) /)

        then:
        eval(/ x /) == new MarcoNumber(1)
    }

    def "defining in terms of other bindings"() {
        when:
        eval(/ (def x 1) /)
        eval(/ (def y x) /)

        then:
        lookUp("y") == new MarcoNumber(1)
    }

    def "trying to bind to an already bound variable"() {
        when:
        eval(/ (def x 1) /)
        eval(/ (def x 2) /)

        then:
        BindingError e = thrown()
        e.binding == "x"
        e.oldValue == new MarcoNumber(1)
    }

    def "returns nil"() {
        expect:
        eval(/ (def xx 11) /) == new MarcoNil()
    }

    def "first parameter is a symbol"() {
        when:
        eval(/ (def 2 1) /)

        then:
        TypeError e = thrown()
        e.expected == MarcoSymbol
        e.actual == new MarcoNumber(2)
    }

    def "without enough arguments"() {
        when:
        eval(/ (def x) /)

        then:
        ContractViolation e = thrown()
        e.expected == 2
        e.actual == 1
    }

    def "with extra arguments"() {
        when:
        eval(/ (def x 1 2) /)

        then:
        ContractViolation e = thrown()
        e.expected == 2
        e.actual == 3
    }
}
