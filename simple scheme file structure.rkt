#lang racket

(define (box x)
  ;; when the second item to cons is not
  ;; a list, we have a pair.
  (cons
   (lambda() x)
   (lambda(y) (set! x y))
  )
)

(define (get-val bx)
  ((car bx)))
(define (set-val! bx new-val)
  ((cdr bx) new-val))


;; An employee object is represented as a list of
;; 3 setter-getter pairs
(define (Employee name position salary)
    (let
        ((bxname (box name))
        (bxpos (box position))
        (bxsal (box salary)))
        (list
            (cons
                (lambda() (get-val bxname))
                (lambda(x) (set-val! bxname x))
            )
            (cons
                (lambda() (get-val bxpos))
                (lambda(x) (set-val! bxpos x))
            )
            (cons
                (lambda() (get-val bxsal))
                (lambda(x) (set-val! bxsal x))
            )
        )
    )
)



(define (get-name emp)
    ((car (list-ref emp 0)))
)
(define (set-name emp new-name)
    ((cdr (list-ref emp 0)) new-name)
)

(define (get-position emp)
    ((car (list-ref emp 1)))
)
(define (set-position emp new-pos)
    ((cdr (list-ref emp 1)) new-pos)
)

(define (get-salary emp)
    ((car (list-ref emp 2)))
)
(define (set-salary emp new-sal)
    ((cdr (list-ref emp 2)) new-sal)
)

(define prof (Employee "Austin" "Professor" 99999999999999999))

(get-name prof)
(get-position prof)
(get-salary prof)

(set-name prof "Tom the Mighty")
(set-position prof "Master of Time and Space")
(set-salary prof 12345678)

(get-name prof)
(get-position prof)
(get-salary prof)