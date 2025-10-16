(ns edn-specifier.gen-spec
  (:require [clojure.spec.alpha :as s]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.edn :as edn]
            [clojure.pprint :as pp]
            [failjure.core :as f]
            [edn-specifier.util :as util]))
(declare gen-spec)

(defn gen-coll-spec
  [e]
  #_`(map #(gen-spec %) e)
  (let [spec (distinct (map #(gen-spec %) e))]
    
    (cond
      (= 0 (count spec)) `any?
      (= 1 (count spec)) (first spec)
      :else (concat `(s/or) spec))))

(defn gen-map-spec
  [m]
  )
(defn gen-spec
  [e]
  (cond
    ;; Simple types
    (number? e) `number?
    (boolean? e) `boolean?
    (string? e) `string?
    (char? e) `char?
    (symbol? e) `symbol?
    (keyword? e) `keyword?
    ;; More complex types
    (vector? e) `(s/coll-of ~(gen-coll-spec e) :kind vector?)
    (set? e) `(s/coll-of (gen-coll-spec e) :kind set?)
    (seq? e) `(s/coll-of ~(gen-coll-spec e) :kind seq?)
    ;; map
    (map? e) (update-vals e #(gen-spec %))
    :else `any?))



(comment
  (gen-spec [1,2]) 
  (gen-coll-spec [1,2])
  (def resource-dir "/home/swift/edn-specifier/resources")
  (def test-files (->> resource-dir
                       io/file
                       file-seq
                       (filter #(.isFile %))
                       (map util/load-edn!)))
  (first test-files)
  (pp/pprint (gen-spec (first test-files)))
  )