// Package bob provides an interface to communicate with bob
package bob

import "strings"
import "regexp"

// Hey is the interface to give bob remarks to, returning his response
func Hey(remark string) string {

	remark = strings.TrimSpace(remark)
	remarkUpper := strings.ToUpper(remark)
	hasLetters, _ := regexp.MatchString("[A-Za-z]", remark)

	var lastChar string

	if len(remark) > 0 {
		lastChar = string(remark[len(remark)-1])
	} else {
		return "Fine. Be that way!"
	}

	switch {

	case lastChar == "?" && remarkUpper == remark && hasLetters:
		return "Calm down, I know what I'm doing!"

	case lastChar == "?":
		return "Sure."

	case remarkUpper == remark && hasLetters:
		return "Whoa, chill out!"

	}

	return "Whatever."
}
