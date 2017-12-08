using UnityEngine;
using System.Collections;

public class Shoot : MonoBehaviour
{
    public GameObject projectile; 

	void Update ()
    {
        // Spawn
        if (Input.GetButtonDown("Fire1"))
            Instantiate(projectile, transform.position, Quaternion.identity);
	}
}
