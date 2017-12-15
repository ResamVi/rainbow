// Package acronym provides an interface for shortening multi-word terms
package acronym

import "strings"
import "regexp"

// Abbreviate s by concatenating the first letter of each word together in upper case
func Abbreviate(s string) string {

	var result string

	regexpr, _ := regexp.Compile("[\\s-]")

	words := regexpr.Split(s, -1)

	for _, word := range words {
		result += string(word[0])
	}

	return strings.ToUpper(result)
}
