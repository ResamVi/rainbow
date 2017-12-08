using UnityEngine;
using System.Collections;

public static class QuestionDatabase {
	
	private static string[] answer;

	static QuestionDatabase() {

		answer = new string[33];
		
		answer [0] = "gefreiter";
		answer [1] = "obergefreiter";
		answer [2] = "hauptgefreiter";
		answer [3] = "stabsgefreiter";
		answer [4] = "oberstabsgefreiter";
		answer [5] = "schütze(ua)";
		answer [6] = "gefreiter(ua)";
		answer [7] = "obergefreiter(ua)";
		answer [8] = "hauptgefreiter(ua)";
		answer [9] = "gefreiter(fa)";
		answer [10] = "gefreiter(oa)";
		answer [11] = "unteroffizier";
		answer [12] = "stabsunteroffizier";
		answer [13] = "feldwebel";
		answer [14] = "oberfeldwebel";
		answer [15] = "hauptfeldwebel";
		answer [16] = "stabsfeldwebel";
		answer [17] = "oberstabsfeldwebel";
		answer [18] = "unteroffizier(fa)";
		answer [19] = "fahnenjunker";
		answer [20] = "fähnrich";
		answer [21] = "oberfähnrich";
		answer [22] = "leutnant";
		answer [23] = "oberleutnant";
		answer [24] = "hauptmann";
		answer [25] = "stabshauptmann";
		answer [26] = "major";
		answer [27] = "oberstleutnant";
		answer [28] = "oberst";
		answer [29] = "brigadegeneral";
		answer [30] = "generalmajor";
		answer [31] = "generalleutnant";
		answer [32] = "general";

	}

	public static string getAnswer(int index) {
		if(index >= 0 && index <= 32)
			return answer[index];
		else
			return "Error";
	}
}
