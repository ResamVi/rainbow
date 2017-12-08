using UnityEngine;
using System.Collections;

public class DestroyOnCollision : MonoBehaviour {

    public string[] ignore;

    void OnCollisionEnter2D(Collision2D coll)
    {
        bool destroy = true;

        for (int i = 0; i < ignore.Length; i++)
        {
            if (coll.gameObject.tag == ignore[i])
                destroy = false;
        }

        if (destroy)
            Destroy(gameObject);
    }
}
