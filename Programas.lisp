(defun fibonacci (n)
  (cond 
    ( (= n 0) 0)
    ( (= n 1) 1)
    (t (+ (fibonacci (- n 1)) (fibonacci (- n 2))))))
    
    (print(fibonacci 10))
    
    (
    defun Factorial (num)
    (if (= num 0) 1
        (* num (Factorial (- num 1)))
    )
)
(print(Factorial 8))

(
    defun TemperatureConversion (num)
    
    (+ 32 (* num 1.8))
)

(print(TemperatureConversion 70))