package tests.marco.lang.functions.hash_map

import helpers.MarcoSpecification

class hash_map extends MarcoSpecification {
    def "one element"() {
        given:
        eval(/ (def :h (hash-map [:a 1])) /)

        expect:
        eval(/ (h :a) /) == eval(/ 1 /)
    }

    def "several elements"() {
        given:
        eval(/ (def :h (hash-map [:a 1 :b 2 :c 3])) /)
        eval(/ (print h) /)

        expect:
        eval(/ (h :a) /) == eval(/ 1 /)
        eval(/ (h :b) /) == eval(/ 2 /)
        eval(/ (h :c) /) == eval(/ 3 /)
    }
}
