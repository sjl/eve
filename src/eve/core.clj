(ns eve.core
  (:use [incanter core io charts stats]))

(def world-population
  (read-dataset "https://raw.github.com/gist/2394233/42b9139fe59bf6aac1e17cffb719e4796792225e/world-population.csv"
                :header true))

(defn number-of-ancestors [x]
  (let [years-ago (- 2012 x)
        generations-ago (int (/ years-ago 20))]
    (Math/pow 2 generations-ago)))

(doto (scatter-plot ($ :year world-population)
                    ($ :population world-population)
                    :x-label "Year"
                    :y-label "People")
  (add-function number-of-ancestors 500 2012)
  (set-x-range 500 2012)
  (set-y-range 0 1300000000)
  view)
