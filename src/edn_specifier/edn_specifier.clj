(ns edn-specifier.edn-specifier
  (:require [clojure.spec.alpha :as s]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.edn :as edn]
            [clojure.pprint :as pp]
            [failjure.core :as f]
            [edn-specifier.util :as util]))

(defn simple?
  "Checks if arg is \"simple\".
   A simple type is one of 
   - nil
   - booleans
   - strings
   - chars
   - symbol
   - keywords
   - nums"
  [t]
  (or (boolean? t)
      (string? t)
      (char? t)
      (symbol? t)
      (keyword? t)
      (number? t)))

(defn complex?
  "Checks if arg is \"complex\".
     A complex type is one of 
     - lists
     - vector
     - set
     - map"
  [t]
  (or (seq? t)
      (vector? t)
      (set? t)
      (map? t)))

(defn compare-type-and-return-spec-or-conflict
  [m1 m2]
  )



(defn compare-edn-maps
  [m1 m2]
  (let [keys (distinct (concat (keys m1) (keys m2)))]
    keys)
  )


(defn specify
  [m1 m2 opt]
  ;; colllect all keys in both maps
  ;; compare each map key and check if:
  ;; - if "simple", check if they are the same and depending on opts, return either spec for key or conflict 
  ;; - if "complex"
  )

(comment
  (def resource-dir "/home/swift/edn-specifier/resources")
  (def test-files (->> resource-dir
                       io/file
                       file-seq
                       (filter #(.isFile %))
                       (map util/load-edn!)))
  (compare-edn-maps
   {:x "Hi", :y ["1" "2" 3], :z {:a true, :b nil, :c :foo}}
   {:x "Hi", :y ["1" "2" 3], :z #{{:a :foo, :b 5}
                                  {:a [1 2 3], :b 0}
                                  {:a 1, :b 4}}})
  (compare-edn-maps {:x "Hi", :y ["1" "2" 3]}
                    {:x "Hi", :y {}, :z true})


  )