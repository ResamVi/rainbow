package edu.kit.informatik;

public class Terminal {
    
    /**
     * Line index
     */
    private static int i = 0;
    
    /**
     * Code to be tested
     * (lines starting with > simulate user input)
     */
    private static final String[] code = {


//// Replace Terminal.java with this  Please add more tests
//
//// CORRECT IF YOU'RE SURE SOMETHING IS NOT CORRECT
//
////                                                                                                              
////                                ,,                                                                            
////  `7MM"""Mq.                  `7MM                                                                            
////    MM   `MM.                   MM                                                                            
////    MM   ,M9  .gP"Ya `7MMpdMAo. MM   ,6"Yb.  ,p6"bo   .gP"Ya                                                  
////    MMmmdM9  ,M'   Yb  MM   `Wb MM  8)   MM 6M'  OO  ,M'   Yb                                                 
////    MM  YM.  8M""""""  MM    M8 MM   ,pm9MM 8M       8M""""""                                                 
////    MM   `Mb.YM.    ,  MM   ,AP MM  8M   MM YM.    , YM.    ,                                                 
////  .JMML. .JMM.`Mbmmd'  MMbmmd'.JMML.`Moo9^Yo.YMbmd'   `Mbmmd'                                                 
////                       MM                                                                                     
////                     .JMML.                                                                                   
////                                                                                                              
////                                               ,,                         ,,     ,,                           
////  MMP""MM""YMM                                 db                       `7MM     db                           
////  P'   MM   `7                                                            MM                                  
////       MM  .gP"Ya `7Mb,od8 `7MMpMMMb.pMMMb.  `7MM  `7MMpMMMb.   ,6"Yb.    MM   `7MM  ,6"Yb.`7M'   `MF',6"Yb.  
////       MM ,M'   Yb  MM' "'   MM    MM    MM    MM    MM    MM  8)   MM    MM     MM 8)   MM  VA   ,V 8)   MM  
////       MM 8M""""""  MM       MM    MM    MM    MM    MM    MM   ,pm9MM    MM     MM  ,pm9MM   VA ,V   ,pm9MM  
////       MM YM.    ,  MM       MM    MM    MM    MM    MM    MM  8M   MM    MM  ,, MM 8M   MM    VVV   8M   MM  
////     .JMML.`Mbmmd'.JMML.   .JMML  JMML  JMML..JMML..JMML  JMML.`Moo9^Yo..JMML.db MM `Moo9^Yo.   W    `Moo9^Yo.
////                                                                              QO MP                           
////                                                                              `bmP                            
////                                                                                                              
////                      ,,          ,,                     ,,          ,,                                       
////                      db   mm   `7MM              mm   `7MM          db                                       
////                           MM     MM              MM     MM                                                   
////  `7M'    ,A    `MF'`7MM mmMMmm   MMpMMMb.      mmMMmm   MMpMMMb.  `7MM  ,pP"Ybd                              
////    VA   ,VAA   ,V    MM   MM     MM    MM        MM     MM    MM    MM  8I   `"                              
////     VA ,V  VA ,V     MM   MM     MM    MM        MM     MM    MM    MM  `YMMMa.                              
////      VVV    VVV      MM   MM     MM    MM        MM     MM    MM    MM  L.   I8                              
////       W      W     .JMML. `Mbmo.JMML  JMML.      `Mbmo.JMML  JMML..JMML.M9mmmP'                              
////                                                                                                              
////                                                                                                              
////                                                                                                              
////              ,,                                                       ,,        ,,                           
////  `7MM"""Mq.`7MM                                                     `7MM      `7MM                           
////    MM   `MM. MM                                                       MM        MM                           
////    MM   ,M9  MM  .gP"Ya   ,6"Yb.  ,pP"Ybd  .gP"Ya       ,6"Yb.   ,M""bMM   ,M""bMM                           
////    MMmmdM9   MM ,M'   Yb 8)   MM  8I   `" ,M'   Yb     8)   MM ,AP    MM ,AP    MM                           
////    MM        MM 8M""""""  ,pm9MM  `YMMMa. 8M""""""      ,pm9MM 8MI    MM 8MI    MM                           
////    MM        MM YM.    , 8M   MM  L.   I8 YM.    ,     8M   MM `Mb    MM `Mb    MM                           
////  .JMML.    .JMML.`Mbmmd' `Moo9^Yo.M9mmmP'  `Mbmmd'     `Moo9^Yo.`Wbmd"MML.`Wbmd"MML.                         
////                                                                                                              
////                                                                                                              
////                                                                                                              
////                                                                                                              
////                                                  mm                     mm                                   
////                                                  MM                     MM                                   
////  `7MMpMMMb.pMMMb.  ,pW"Wq.`7Mb,od8 .gP"Ya      mmMMmm .gP"Ya  ,pP"Ybd mmMMmm ,pP"Ybd                         
////    MM    MM    MM 6W'   `Wb MM' "',M'   Yb       MM  ,M'   Yb 8I   `"   MM   8I   `"                         
////    MM    MM    MM 8M     M8 MM    8M""""""       MM  8M"""""" `YMMMa.   MM   `YMMMa.                         
////    MM    MM    MM YA.   ,A9 MM    YM.    ,       MM  YM.    , L.   I8   MM   L.   I8                         
////  .JMML  JMML  JMML.`Ybmd9'.JMML.   `Mbmmd'       `Mbmo`Mbmmd' M9mmmP'   `MbmoM9mmmP'                         
////                                                                                                              
////                                                                                                              




            //C1
            "> add author Eniola,Lowry",
            "Ok",
            "> add author Richard,Rhinelander",
            "Ok",
            "> add author Testi,Test",
            "Ok",
            "> add author Eniola,Lowry",
            "Error,",
            "> add author Shashi,Afolabi",
            "Ok",
            "> add author Sergey,Brin",
            "Ok",
            "> add author 1337,1337",
            "Error,",
            
            //C2
            "> add journalTSE,IEEE",
            "Error,",
            "> add journal TSE,IEEE,damn",
            "Error,",
            "> add journal TSE,",
            "Error,",
            "> add journal TSE,IEEE",
            "Ok",
            "> add journal TSE,IEEE",
            "Error,",

            //C3
            "> add conference series ICSA",
            "Ok",
            "> add conference series ICSA",
            "Error,",
            "> add conference series 409jij",
            "Error,",

            //C4
            "> add conference ICSA,2017,Gothenburg",
            "Ok",
            "> add conference ICSA,2017,Gothenburg",
            "Error,",
            "> add conference damn,2017,Gothenburg",
            "Error,",

            //C5
            "> add article to series ICSA:rr2017,2017,Components still have no interfaces",
            "Ok",
            "> add article to journal TSE:mvp2015,2015,Model Consistency",
            "Ok",
            "> add article to journal TSE:sommerville2015,2015,Model Consistency",
            "Ok",
            "> add article to journal TSE:mvp2016,2016,Better Model Consistency",
            "Ok",
            "> add article to journal TSE:nngrade,2016,Better Consistency Consistency",
            "Ok",
            "> add article to journal TSE:mvp2016,2016,Better Better Consistency",
            "Error,",
            "> add article to journal DAMN:mvp2016,2016,Model Model Consistency",
            "Error,",

            //C6
            "> written-by mvp2015,Shashi Afolabi;Richard Rhinelander",
            "Ok",
            "> written-by mvp2015,Shashi Afolabi;Richard Rhinelander",
            "Error,",
            "> written-by rr2017,Richard Rhinelander",
            "Ok",
            "> written-by nngrade,Eniola Lowry",
            "Ok",
            "> written-by mvp2016,Shashi Afolabi;Eniola Lowry",
            "Ok",

            //C7
            "> cites rr2017,mvp2016",
            "Ok",
            "> cites rr2017,sommerville2015",
            "Ok",
            "> cites rr2017,sommerville2015",
            "Error,",
            "> cites rr2017,mvp2015",
            "Ok",
            "> cites rr2017,rr2017",
            "Error,",

            //C8
            "> add keywords to pub sommerville2015:swe;reference;java;oop",
            "Ok",
            "> add keywords to pub rr2017:mdsd;components;java",

            "Ok",
            "> add keywords to pub mvp2016:mdsd;java;oop",
            "Ok",
            "> add keywords to journal TSE:swe",
            "Ok",
            "> add keywords to series ICSA:swe;performance",
            "Ok",
            "> add keywords to conference ICSA,2017:swe;performance;key",
            "Ok",
            "> add keywords to series ICPE:swe;performance",
            "Error,",

            //C9
            "> all publications",
            "rr2017",
            "mvp2015",
            "sommerville2015",
            "mvp2016",
            "nngrade",

            //C10
            "> list invalid publications",
            "sommerville2015",

            //C11
            "> publications by unnamed author;Eniola Lowry",
            "Error,",
            "> publications by unnamed author",
            "Error,",
            "> publications by Eniola Lowry",
            "nngrade",
            "mvp2016",

            //C12
            "> in proceedings ICSA,2017",
            "rr2017",
            "> in proceedings QoSA,2016",
            "Error,",
            "> in proceedings uksa,2016",
            "Error,",
            "> in proceedings QoSA,2015",
            "Error,",

            //C13
            "> find keywords swe,reference",
            "sommerville2015",
            "> find keywords swe,reference,trivial",
            "> find keywords mdsd",
            "rr2017",
            "mvp2016",
            "> find keywords swe,performance",
            "rr2017",

            //C14
            "> jaccard a;b;c d;e",
            "0.000",
            "> jaccard a;b;c;d;e b;c;d;e;f",
            "0.666",

            "> jaccard a b",
            "0.000",

            //C15
            "> similarity rr2017,mvp2016",
            //rr2017:  mdsd, components, (ICSA) java & swe, performance & (2017) swe. performance, key
            //mvp2016  (TSE)  swe, (self) mdsd, java, oop
            //intercept: 3
            //union: 9    -> jac: 3/9 ,
            "0.428",

            //C16
            "> direct h-index 17;3;1;5",
            "3",
            "> direct h-index 8;6;8;4;8;6",
            "5",

            //C17
            "> h-index Eniola Lowry",
            "1",
            "> h-index Shashi Afolabi",
            "1",

            //C18
            "> coauthors of Shashi Afolabi,wqeqw",
            "Error,",
            "> coauthors of Shashi Afolabi",
            "Eniola Lowry",
            "Richard Rhinelander",
            "> coauthors of Eniola Lowry",
            "Shashi Afolabi",

            //C19   probably not correct
            "> foreign citations of Shashi Afolabi",
            "mvp2015",

            //C20
            "> direct print conference ieee:Sergey Brin,Lawrence Page,,The Anatomy of a Large-Scale Hypertextual Web Search Engine,WWW,Brisbane Australia,1998",
            "[1] S. Brin and L. Page, \"The Anatomy of a Large-Scale Hypertextual Web Search Engine,\" in Proceedings of WWW, Brisbane Australia, 1998.",

            //C20
            "> direct print journal ieee:Edsger Dijkstra,,,Go To Statement Considered Harmful,Comm. of the ACM,1968",
            "[1] E. Dijkstra, \"Go To Statement Considered Harmful,\" Comm. of the ACM, 1968.",


            //C21

            "> print bibliography ieee:mvp2016;mvp2015;rr2017",
            "[1] S. Afolabi and E. Lowry, \"Better Model Consistency,\" TSE, 2016.",
            "[2] S. Afolabi and R. Rhinelander, \"Model Consistency,\" TSE, 2015.",
            "[3] R. Rhinelander, \"Components still have no interfaces,\" in Proceedings of ICSA, Gothenburg, 2017.",
            "> print bibliography chicago:rr2017;mvp2016;mvp2015;rr2017",
            "(Afolabi, 2016) Afolabi, Shashi, and Lowry, Eniola. \"Better Model Consistency.\" TSE (2016).",
            "(Afolabi, 2015) Afolabi, Shashi, and Rhinelander, Richard. \"Model Consistency.\" TSE (2015).",
            "(Rhinelander, 2017) Rhinelander, Richard. \"Components still have no interfaces.\" Paper presented at ICSA, 2017, Gothenburg.",

            // misc
            "> a",
            "Error,",
            "> a;a;B",
            "Error,",
            "> List-professor",
            "Error,",
            
            // quit ^^
            "> Quit",
            "Error,",
            "> quit ;",
            "Error,",

            "> quit",
            "Ok",

    };
    
    /**
     * Test Terminal.printLine()
     */
    public static void printLine(String s) {
        
        // log
        System.out.println("(" + (i + 1) + "/" + code.length + ") " + code[i]+ " ~~~ \n ~~~~~~~~ " + s);
        System.out.println();
        
        // check if input expected
        if (code[i].startsWith(">"))
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ Problem at line " + i + ": Input expected. Your output was too long.");
        
        // check if output matches
        if (!code[i].equals(s))
            if (!(code[i].equals("Error,") && s.startsWith(code[i])))
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@  Problem at line " + (i + 1) + ": Output is incorrect: " + s);
        
        i++;
    }
    
    /**
     * Test Terminal.printError()
     */
    public static void printError(String s) {
        printLine("Error, " + s);
    }

    /**
     * Test Terminal.readLine()
     */
    public static String readLine() {
        
        // log
        System.out.println("(" + (i + 1) + "/" + code.length + ") " + code[i]);

        // check if output expected
        if (!code[i].startsWith(">"))
            System.out.println("@ @ @ @ @ @ @ @ @ !!! Problem at line " + i++ + ": Output expected. Your output was too short.");

        // think like an user, act like an user
        return code[i++].substring(2);
    }
    
}