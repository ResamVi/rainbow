package space

// Age calculates age in many different planet-years
func Age(seconds float64, planet string) float64 {

	earth_years := seconds / 31557600

	switch planet {

	case "Earth":
		return earth_years
	case "Mercury":
		return earth_years / 0.2408467
	case "Venus":
		return earth_years / 0.61519726
	case "Mars":
		return earth_years / 1.8808158
	case "Jupiter":
		return earth_years / 11.862615
	case "Saturn":
		return earth_years / 29.447498
	case "Uranus":
		return earth_years / 84.016846
	case "Neptune":
		return earth_years / 164.79132

	}

	return -1
}
