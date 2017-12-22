package main

import "fmt"

func Fct(n uint) uint {

	var prev float64 = 1
	var current float64 = 2

	switch n {
	case 0:
		return 1
	case 1:
		return 2
	}

	for i := 0; uint(i) < n-1; i++ {

		next := (6 * prev * current) / (5*prev - current)

		tmp := current
		prev = tmp
		current = next
	}
	return uint(current)
}

func main() {
	for i := 0; i < 50; i++ {
		fmt.Printf("%d: ", i)
		fmt.Println(Fct(uint(i)))
	}
	//Fct(35)
}
