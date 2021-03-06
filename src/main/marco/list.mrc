(def :cons (function [:head :tail] {
  (with-meta (pair head tail) #{:length (+ 1 ((meta tail) :length))})
}))

(def :head @"Returns the head of a list"
  (function [:list] {
    (first list)
  })
)

(def :tail @"Returns the tail of a list"
  (function [:list] {
    (second list)
  })
)

(def :length (function [:list] {
  ((meta list) :length)
}))

(def :list? (function [:list] {
  (if (nil? list) { true } {
    (if (pair? list) {
      (recurse (tail list))
    } {
      false
    })
  })
}))

(export [:cons :length :list? :head :tail])
