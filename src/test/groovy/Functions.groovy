import helpers.MarcoSpecification
import marco.lang.error.LookUpError
import marco.lang.exceptions.ContractViolation
import marco.lang.exceptions.TypeException

class Functions extends MarcoSpecification {
    def "type"() {
        expect:
        eval(/ (function? (function [] { 1 })) /) == eval(/ true /)
        eval(/ (function? 1) /) == eval(/ false /)
        eval(/ (function? def) /) == eval(/ true /)
    }

    def "zero argument function"() {
        when:
        eval(/ (def :some_function (function [] { 4 })) /)

        then:
        eval(/ (some_function) /) == eval(/ 4 /)
    }

    def "takes 1 parameter and returns it"() {
        when:
        eval(/ (def :f (function [:x] { x })) /)

        then:
        eval(/ (f 42) /) == eval(/ 42 /)
    }

    def "takes 2 parameters and returns the second"() {
        when:
        eval(/ (def :f (function [:x :y] { y })) /)

        then:
        eval(/ (f 3 4) /) == eval(/ 4 /)
    }

    def "parameters shadow previous bindings"() {
        when:
        eval(/ (def :x 1) /)
        eval(/ (def :f (function [:x] {x})) /)

        then:
        eval(/ (f 2) /) == eval(/ 2 /)
    }

    def "previous environment is available to function body"() {
        when:
        eval(/ (def :p 5) /)
        eval(/ (def :f (function [:x] { p })) /)

        then:
        eval(/ (f 3) /) == eval(/ 5 /)
    }

    def "cannot access variable that is not defined"() {
        given:
        eval(/ (def :f (function [] { s })) /)

        when:
        eval(/ (f) /)

        then:
        LookUpError e = thrown()
        e.binding == "s"
    }

    def "cannot access variable before its definition"() {
        given:
        eval(/ (def :f (function [] { s })) /)
        eval(/ (def :s 1) /)

        when:
        eval(/ (f) /)

        then:
        LookUpError e = thrown()
        e.binding == "s"
    }

    def "error when argument is not a list"() {
        when:
        eval(/ (def :f (function 1 { x })) /)

        then:
        TypeException e = thrown()
        e.expected == "List"
        e.actual == eval(/ 1 /)
    }

    def "error when arguments aren't symbols"() {
        when:
        eval(/ (def :f (function [:x :y 1] { x })) /)

        then:
        TypeException e = thrown()
        e.expected == "Symbol"
        e.actual == eval(/ 1 /)
    }

    def "error when defining a function with too few arguments"() {
        when:
        eval(/ (def :f (function 1)) /)

        then:
        ContractViolation e = thrown()
        e.expected == 2
        e.actual == 1
    }

    def "error when defining a function with too many arguments"() {
        when:
        eval(/ (def :f (function [:x] 1 1)) /)

        then:
        ContractViolation e = thrown()
        e.expected == 2
        e.actual == 3
    }

    def "call with too many arguments"() {
        given:
        eval(/ (def :f (function [:x] { x })) /)

        when:
        eval(/ (f 1 2) /)

        then:
        ContractViolation e = thrown()
        e.expected == 1
        e.actual == 2
    }

    def "call with too few arguments"() {
        given:
        eval(/ (def :f (function [:x :y] { x })) /)

        when:
        eval(/ (f 1) /)

        then:
        ContractViolation e = thrown()
        e.expected == 2
        e.actual == 1
    }

    def "calls another function"() {
        given:
        eval(/ (def :f (function [] { true })) /)
        eval(/ (def :g (function [] { (f) })) /)

        when:
        eval(/ (def :result (g)) /)

        then:
        eval(/ result /) == eval(/ true /)
    }

    def "nested"() {
        given:
        eval(/ (def :t 1) /)
        eval(/ (def :f (function [:n] { (+ n ((function [:x] { (+ (+ x n) t) }) n)) })) /)

        expect:
        eval(/ (f 1) /) == eval(/ 4 /)
    }
}
