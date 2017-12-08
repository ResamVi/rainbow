package gigasecond

import "time"
import "math"

// AddGigasecond adds a 10^9 seconds to the given time and returns the result
func AddGigasecond(t time.Time) time.Time {
	return t.Add(time.Duration(math.Pow(10, 9)) * time.Second)
}
