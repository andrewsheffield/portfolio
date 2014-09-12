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

(define programmer1 (Employee "Andrew" "Code Master" 1))

(get-name programmer1)
(get-position programmer1)
(get-salary programmer1)

(set-name programmer1 "Andrew J Sheffield")
(set-position programmer1 "Commander of the Universe")
(set-salary programmer1 2)
;;Got a pay raise! lol

(get-name programmer1)
(get-position programmer1)
(get-salary programmer1)