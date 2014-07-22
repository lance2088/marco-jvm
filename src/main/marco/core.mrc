(def :for-each (function [:list :f] {
  (if (nil? list) { nil }
    { (f (head list)) (recurse (tail list) f) } )
}))

(def :even? (function [:v] { (= (% v 2) 0) }))

(def :max (function [:v1 :v2] { (if (> v1 v2) {v1} {v2}) }))

(def :or (function [:b1 :b2] {
  (let [:v1 { (invoke b1) }] {
    (if v1 { v1 } { (invoke b2) })
  })
}))

(export [:for-each :even? :max :or])
