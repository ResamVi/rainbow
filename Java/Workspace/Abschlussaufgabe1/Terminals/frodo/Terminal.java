package edu.kit.informatik;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Terminal {

 private static final String[] code = new String[] {

   // checks for wrong commands
   " wrongCommandWithLeadingWhitespace", "Error, whitespace / unknown command",
   "wrongCommand", "Error, unknown command",

   // check for empty output
   "all publications", "#",
   "list invalid publications", "#",

   // adding correct authors (case sensitivity)
   "add author Albert,Einstein", "Ok",
   "add author albert,Einstein", "Ok",
   "add author Albert,einstein", "Ok",
   "add author albert,einstein", "Ok",

   // adding correct authors
   "add author Marie,Curie", "Ok",
   "add author Niels,Bohr", "Ok",
   "add author Peter,Higgs", "Ok",
   "add author Harald,Lesch", "Ok",

   // trying to add incorrect authors
   " add author Marie,Curie", "Error, leading whitespace",
   "add author Marie,Curie", "Error, author already existing",
   "add author Niels,Bohr", "Error, author already existing",
   "add author Peter,Higgs", "Error, author already existing",
   "add author Albert,Einstein", "Error, author already existing",
   "add author  Marie,Curie", "Error, trailing whitespace",
   "add author Marie ,Curie", "Error, input containing whitespace",
   "add author Marie, Curie", "Error, input containing whitespace",
   "add author Albert,Einstein", "Error, author already existing",
   "add author Albert,1stein", "Error, input contains wrong characters",
   "add author Alber!,Einstein", "Error, input contains wrong characters",
   "add author (Albert),[Einstein]", "Error, input contains wrong characters",
   "add author Albert,Einstein,the,first", "Error, invalid number of arguments",
   "add author Albert", "Error, invalid number of arguments",
   "add author ,", "Error, invalid number of arguments",
   "add author ", "Error, invalid number of arguments",

   // adding correct journals
   "add journal Journal1,Albert", "Ok",
   "add journal Journal2,Marie", "Ok",
   "add journal Journal3,Harald", "Ok",
   "add journal Journal1 ,Thomas", "Ok",
   "add journal  Journal1,Thomas", "Ok",
   "add journal Jour nal1,Thomas", "Ok",

   // trying to add incorrect journals
   "add journal TSE,IEEE,wea", "Error, invalid number of arguments",
   "add journal TSE,", "Error, invalid number of arguments",
   "add journal Journal1 ,Thomas", "Error, already existing",
   "add journal ", "Error, invalid number of arguments",

   // adding correct conference series
   "add conference series Informatik", "Ok",
   "add conference series Relativitaetstheorie", "Ok",
   "add conference series Inform atik", "Ok",
   "add conference series Relativitaetstheorie ", "Ok",

   // trying to add incorrect conference series
   "add conference series Informatik", "Error, already existing",
   "add conference series ", "Error, invalid number of arguments",
   " add conference series Informatik", "Error, leading whitespace",
   "add  conference series Informatik", "Error, invalid command",

   // adding correct conferences
   "add conference Informatik,2017,Karlsruhe", "Ok",
   "add conference Informatik,2016,Karlsruhe", "Ok",
   "add conference Informatik,2015,Karlsruhe", "Ok",
   "add conference Informatik,2014,Karlsruhe", "Ok",
   "add conference Informatik,2013,Karlsruhe", "Ok",
   "add conference Relativitaetstheorie,1999,Karlsruhe", "Ok",
   "add conference Relativitaetstheorie,1998,Karlsruhe", "Ok",
   "add conference Relativitaetstheorie,1997,Karlsruhe", "Ok",
   "add conference Relativitaetstheorie ,1996,Karlsruhe", "Ok",

   // trying to add incorrect conferences
   "add conference ICSA,2017,Gothenburg", "Error, conference series not existing",
   "add conference Informatik,2017,Hardenhausen", "Error, conference in this year existing",
   "add conference Informatik,2017,Hardenhausen,", "Error, ends with separator",
   "add conference Informatik,adsf,Gothenburg", "Error, no valid year",
   "add conference ", "Error, invalid number of arguments",
   "add conference Informatik,adsf,", "Error, invalid number of arguments",
   "add conference Informatik,adsf", "Error, invalid number of arguments",
   "add conference Informatik,", "Error, invalid number of arguments",
   "add conference Informatik", "Error, invalid number of arguments",
   "add conference ,", "Error, invalid number of arguments",

   // adding correct articles
   "add article to series Informatik:informatik2017,2017,informatik2017", "Ok",
   "add article to series Informatik:informatik2016,2016,informatik2016", "Ok",
   "add article to series Informatik:informatik2015,2015,informatik2015", "Ok",
   "add article to series Relativitaetstheorie:relativitaetstheorie1999,1999,title is irrelevant", "Ok",
   "add article to series Relativitaetstheorie:relativitaetstheorie1998,1998,title is irrelevant", "Ok",
   "add article to series Relativitaetstheorie:relativitaetstheorie1997,1997,title is irrelevant", "Ok",
   "add article to series Relativitaetstheorie :relativitaetstheorie1996,1996,title is irrelevant", "Ok",
   "add article to journal Journal1:journal1firstarticle,2015,title is irrelevant", "Ok",

   // adding more valid articles
   "add article to series Informatik:article1,2017,title one", "Ok",
   "add article to series Informatik:article2,2017,title two", "Ok",
   "add article to series Informatik:article3,2014,title three", "Ok",
   "add article to series Informatik:article4,2013,title four", "Ok",

   // trying to add invalid articles
   "add article to series Informatik:article1,asdf,informatik2017", "Error, no valid year",
   "add article to series Informatika:article1,2017,informatik2017", "Error, series not existing",
//   "add article to series Informatik:Article1,2017,informatik2017", "Error, article identifier not valid",
//   "add article to series Informatik:artic!le1,2017,informatik2017", "Error, article identifier not valid",
//   "add article to series Informatik:article1 ,2017,informatik2017", "Error, article identifier not valid",
   "add article to series :article1,2017,informatik2017", "Error, invalid series",
   "add article to series Informatik:2017,informatik2017", "Error, invalid number of arguments",
   "add article to series Informatik:,,informatik2017", "Error, invalid number of arguments",
   "add article to series Informatik:", "Error, invalid number of arguments",

   // first output for publications
   "all publications", "#informatik2017 informatik2016 informatik2015 "
     + "relativitaetstheorie1999 relativitaetstheorie1998 relativitaetstheorie1997 journal1firstarticle " +
     "article1 article2 article3 article4 relativitaetstheorie1996 ",
   "list invalid publications", "#informatik2017 informatik2016 informatik2015 "
     + "relativitaetstheorie1999 relativitaetstheorie1998 relativitaetstheorie1997 journal1firstarticle " +
     "article1 article2 article3 article4 relativitaetstheorie1996",

   // naming authors
   "written-by article1,Albert Einstein", "Ok",
   "written-by article2,Marie Curie", "Ok",
   "written-by article3,Albert Einstein;Marie Curie", "Ok",
   "written-by article4,Albert Einstein;Marie Curie", "Ok",
   "written-by article4,Niels Bohr", "Ok",

   // changed number of invalid publications
   "list invalid publications", "#informatik2017 informatik2016 informatik2015 "
     + "relativitaetstheorie1999 relativitaetstheorie1998 relativitaetstheorie1997 journal1firstarticle relativitaetstheorie1996 ",

   // adding more authors
   "written-by informatik2017,Harald Lesch", "Ok",
   "written-by informatik2016,Harald Lesch", "Ok",
   "written-by informatik2015,Harald Lesch", "Ok",
   "written-by relativitaetstheorie1999,Peter Higgs", "Ok",
   "written-by relativitaetstheorie1998,Peter Higgs", "Ok",
   "written-by relativitaetstheorie1997,Peter Higgs;Harald Lesch", "Ok",

   // trying to add invalid authors
   "written-by informatik2017,Shashi Aflosbi", "Error, author not valid",
   "written-by Informatik2013,Albert Einstein", "Error, publication not valid",
   "written-by informatik2017,Harald Lesch", "Error, already listed",
   "written-by informatik2017,Peter Higgs;Harald Lesch", "Error, already listed",
   "written-by informatik2017,", "Error, input invalid",
   "written-by ", "Error, input invalid",
   "written-by ,,", "Error, input invalid",

   // last time invalid publications
   "list invalid publications", "#journal1firstarticle relativitaetstheorie1996",
   "written-by journal1firstarticle,Peter Higgs", "Ok",
   "written-by relativitaetstheorie1996,Niels Bohr", "Ok",
   "list invalid publications", "#",

   // adding cites
   "cites article1,article3", "Ok",
   "cites article1,article4", "Ok",
   "cites article2,article3", "Ok",
   "cites article2,article4", "Ok",
   "cites informatik2017,relativitaetstheorie1999", "Ok",
   "cites informatik2017,article3", "Ok",
   "cites informatik2017,article4", "Ok",
   "cites informatik2017,informatik2016", "Ok",
   "cites article1,relativitaetstheorie1999", "Ok",
   "cites article2,informatik2016", "Ok",
   "cites article1,informatik2016", "Ok",
   "cites article2,relativitaetstheorie1999", "Ok",
   "cites article1,relativitaetstheorie1998", "Ok",
   "cites article2,relativitaetstheorie1998", "Ok",
   "cites article3,relativitaetstheorie1998", "Ok",
   "cites article1,relativitaetstheorie1997", "Ok",
   "cites article2,relativitaetstheorie1997", "Ok",
   "cites article3,relativitaetstheorie1997", "Ok",

   // adding invalid cites
   "cites rr2017,mvp2016", "Error, article not existing",
   "cites article1,article2", "Error, year has to be before",
   "cites article1,article1", "Error, year has to be before/cant cite itself",
   "cites ,article1", "Error, invalid number of arguments",
   "cites article1,", "Error, invalid number of arguments",
   "cites ,", "Error, invalid number of arguments",
   "cites ,,", "Error, invalid number of arguments",
   "cites ", "Error, invalid number of arguments",

   // adding keywords
   "add keywords to pub article1:article", "Ok",
   "add keywords to pub article2:art", "Ok",
   "add keywords to pub article3:arti", "Ok",
   "add keywords to series Informatik:informatik;info", "Ok",
   "add keywords to series Relativitaetstheorie:rela;relativitaet", "Ok",
   "add keywords to journal Journal1:journal;jour", "Ok",
   "add keywords to conference Informatik,2013:randomkeyword;conferencescanbeadded", "Ok",

   // trying to add invalid keywords
   "add keywords to pub article1:Article", "Error, upper case letter",
   "add keywords to pub article1:ar!icle", "Error, unknown character",
   "add keywords to pub article1:art icle", "Error, whitespace",
   "add keywords to pub article:article", "Error, no valid publication",
   "add keywords to series WrongSeries:rela;relativitaet", "Error, series not existing",
   "add keywords to journal WrongJournal:journal;jour", "Error, journal not existing",
   "add keywords to pub article1:", "Error, invalid number of arguments",
   "add keywords to pub :article", "Error, invalid number of arguments",
   "add keywords to pub :", "Error, invalid number of arguments",
   "add keywords to pub ", "Error, invalid number of arguments",

   // check publications
   "publications by Albert Einstein", "#article1 article3 article4",
   "publications by Niels Bohr", "#article4 relativitaetstheorie1996",
   "publications by Harald Lesch", "#informatik2017 informatik2016 informatik2015 relativitaetstheorie1997",

   // invalid publication checks
   "publications by Eniola Lowry", "Error, author not valid",
   "publications by 123 456", "Error, author not valid",
   "publications by Albert Einstein Drei", "Error, invalid number of arguments",
   "publications by Eniola ", "Error, input not valid",

   // in proceedings
   "in proceedings Informatik,2017", "#informatik2017 article1 article2",
   "in proceedings Informatik,2016", "#informatik2016",

   // wrong proceedings
   "in proceedings QoSA,2016", "Error, series not valid",
   "in proceedings 123,2016", "Error, series not valid",
   "in proceedings Informatik,1999", "Error, conference in this year not existing",
   "in proceedings Informatik,asdf", "Error, no valid year",
   "in proceedings Informatik,asdf,", "Error, ends with separator",
   "in proceedings Informatik", "Error, invalid argument number",
   "in proceedings Informatik,1999,what", "Error, invalid argument number",

   // find valid keywords
   "find keywords informatik", "#informatik2017 informatik2016 informatik2015 article1 article2 article3 article4",
   "find keywords heyho;trivial", "#",
   "find keywords informatik;article", "#article1",
   "find keywords informatik;art", "#article2",
   "find keywords jour", "#journal1firstarticle",

   // invalid keywords requests
   "find keywords ", "Error, empty sets are not allowed as input",
   "find keywords ,what,", "Error, ends with separator",
   "find keywords Drei", "Error, invalid keyword",

   // jaccard
   "jaccard a;b;c d;e", "0.000\n",
   "jaccard a;b;c;d;e b;c;d;e;f", "0.666\n",
   "jaccard a;b;c;d;e;f;g;h;i;j;h b;c;d;e;f", "0.500\n",
   "jaccard wow;hi;zwei;droelf;sans;eso hi;droelf;es;desto", "0.250\n",
   "jaccard a b", "0.000\n",

   // invalid jaccard requests
   "jaccard a;b; c;d;e b;c;d;e;f", "Error, invalid argument number",
   "jaccard ; ;", "Error, invalid args number/empty sets not allowed at direct input",
   "jaccard ", "Error, invalid args number/empty sets not allowed at direct input",
   "jaccard a,b b,c", "Error, invalid characters",

   // similarity
   "similarity article1,article2", "0.500\n",
   "similarity article1,article3", "0.500\n",
   "similarity article1,article4", "0.400\n",
   "similarity journal1firstarticle,relativitaetstheorie1999", "0.000\n",

   // invalid similarity
   "similarity ,article2", "Error, invalid argument number",
   "similarity article1,", "Error, invalid argument number",
   "similarity article1, ", "Error, invalid argument number / unknown article",
   "similarity ", "Error, invalid argument number / emty sets are not allowed",
   "similarity ,", "Error, invalid argument number / empty sets are not allowed / ends with separator",

   // direct h index
   "direct h-index 17;3;1;5", "3\n",
   "direct h-index 8;6;8;4;8;6", "5\n",
   "direct h-index 8;6;8;4;8;6;4;4;3;2;4;4;9;9;9;9;9;9", "8\n",
   "direct h-index 4;4;4;3", "3\n",
   "direct h-index 4;4;4;3;4", "4\n",

   // wrong direct h index
   "direct h-index 17,3;1;5", "Error, wrong characters",
   "direct h-index 17;drei;1;5", "Error, wrong characters",
   "direct h-index 17;-3;1;5", "Error, negative numbers are not allowed",
   "direct h-index ", "Error, empty sets are not allowed",
   "direct h-index , ,", "Error, empty sets are not allowed / wrong characters / ends with separator",

   // h index of authors
   "h-index Albert Einstein", "2\n",
   "h-index Marie Curie", "2\n",
   "h-index Harald Lesch", "2\n",
   "h-index Peter Higgs", "3\n",

   // wrong h index of authors
   "h-index Alber Einstein", "Error, author not valid",
   "h-index 123 Einstein", "Error, author not valid",
   "h-index Einstein", "Error, invalid number of arguments",
   "h-index ", "Error, invalid number of arguments / empty input",
   "h-index ,", "Error, invalid number of arguments / empty input / ends with separator",

   // valid coAuthors
   "coauthors of Albert Einstein", "#Marie Curie Niels Bohr",
   "coauthors of Peter Higgs", "#Harald Lesch",
   "coauthors of albert einstein", "#",

   // invalid coAuthors
   "coauthors of Alber Einstein", "Error, author not listed",
   "coauthors of Einstein", "Error, author not listed / invalid input",
   "coauthors of ", "Error, invalid input / author not listed",

   // TODO Order relevant ? I have sorted them cause there is a given article order.
   // foreign citations
   "foreign citations of Albert Einstein", "#informatik2017",
   "foreign citations of Marie Curie", "#informatik2017",
   "foreign citations of Niels Bohr", "#informatik2017",
   "foreign citations of Peter Higgs", "#article1 article2 article3",
   "foreign citations of Harald Lesch", "#article1 article2 article3",

   // invalid foreign citations
   "foreign citations of Alber Einstein", "Error, no valid author",
   "foreign citations of Albert", "Error, argument number wrong",
   "foreign citations of Albert 123", "Error, author not valid",
   "foreign citations of ", "Error, argument number wrong / empty sets",
   "foreign citations of ,", "Error, argument number wrong / ends with separator",

   // direct print conference
   "direct print conference ieee:Sergey Brin,,,"
     + "The Anatomy of a Large-Scale Hypertextual Web Search"
     + " Engine,WWW,Brisbane Australia,1998",
   "[1] S. Brin, \"The Anatomy of a Large"
     + "-Scale Hypertextual Web Search Engine,\" in Proceedings"
     + " of WWW, Brisbane Australia, 1998.\n",

   "direct print conference ieee:Sergey Brin,Lawrence Page,,"
     + "The Anatomy of a Large-Scale Hypertextual Web Search"
     + " Engine,WWW,Brisbane Australia,1998",
   "[1] S. Brin and L. Page, \"The Anatomy of a Large"
     + "-Scale Hypertextual Web Search Engine,\" in Proceedings"
     + " of WWW, Brisbane Australia, 1998.\n",

   "direct print conference ieee:Sergey Brin,Lawrence Page,Harald Lesch,"
     + "The Anatomy of a Large-Scale Hypertextual Web Search"
     + " Engine,WWW,Brisbane Australia,1998",
   "[1] S. Brin et al., \"The Anatomy of a Large"
     + "-Scale Hypertextual Web Search Engine,\" in Proceedings"
     + " of WWW, Brisbane Australia, 1998.\n",

   "direct print conference chicago:Sergey Brin,,," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "(Brin, 1998) Brin, Sergey. " +
     "\"The Anatomy of a Large-Scale Hypertextual Web Search Engine.\" " +
     "Paper presented at WWW, 1998, Brisbane Australia.\n",

   "direct print conference chicago:Sergey Brin,Lawrence Page,," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "(Brin, 1998) Brin, Sergey, and Page, Lawrence. " +
     "\"The Anatomy of a Large-Scale Hypertextual Web Search Engine.\" " +
     "Paper presented at WWW, 1998, Brisbane Australia.\n",

   "direct print conference chicago:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "(Brin, 1998) Brin, Sergey, Page, Lawrence, and Lesch, Harald. " +
     "\"The Anatomy of a Large-Scale Hypertextual Web Search Engine.\" " +
     "Paper presented at WWW, 1998, Brisbane Australia.\n",

   // invalid direct prints
   "direct print conference chicago:Sergey Brin,Lawrence ,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "Error, invalid number of arguments",

   "direct print conference chicago:Sergey Brin,Sergey Brin,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "Error, duplicate Author",

   "direct print conference chicago:Sergey Brin,Harald Lesch,Sergey Brin," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "Error, duplicate Author",

   // SEE: https://ilias.studium.kit.edu/goto.php?target=frm_583580_83668&client_id=produktiv
   
   /*"direct print conference chicago:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertex;tual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "Error, wrong format of title",*/

   /*"direct print conference chicago:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WW;W,Brisbane Australia,1998",
   "Error, wrong format of series name",*/

   /*"direct print conference chicago:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Austra;lia,1998",
   "Error, wrong format of location",*/

   /*"direct print conference chicago:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998,",
   "Error, wrong format of year / invalid nr of args",*/ 

   "direct print conference ieee:Sergey Brin,Sergey Brin,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "Error, duplicate Author",

   "direct print conference ieee:Sergey Brin,Harald Lesch,Sergey Brin," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "Error, duplicate Author",

   /*"direct print conference ieee:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertex;tual Web Search" +
     " Engine,WWW,Brisbane Australia,1998",
   "Error, wrong format of title",*/

   /*"direct print conference ieee:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WW;W,Brisbane Australia,1998",
   "Error, wrong format of series name",*/

   /*"direct print conference ieee:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Austra;lia,1998",
   "Error, wrong format of location",*/

   "direct print conference ieee:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,Brisbane Australia,1998;",
   "Error, wrong format of year",
   
   "direct print conference ieee:",
   "Error, wrong format / invalid input",
   
   "direct print conference chicago:",
   "Error, wrong format / invalid input",

   // direct print journal
   "direct print journal ieee:Edsger Dijkstra,,,Go To Statement Considered Harmful," +
     "Comm. of the ACM,1968",
   "[1] E. Dijkstra, \"Go To Statement Considered" +
     " Harmful,\" Comm. of the ACM, 1968.\n",

   "direct print journal ieee:Edsger Dijkstra,Albert Einstein,,Go To Statement Considered Harmful," +
     "Comm. of the ACM,1968",
   "[1] E. Dijkstra and A. Einstein, \"Go To Statement Considered" +
     " Harmful,\" Comm. of the ACM, 1968.\n",

   "direct print journal ieee:Edsger Dijkstra,Albert Einstein,Harald Lesch,Go To Statement Considered Harmful," +
     "Comm. of the ACM,1968",
   "[1] E. Dijkstra et al., \"Go To Statement Considered" +
     " Harmful,\" Comm. of the ACM, 1968.\n",

   "direct print journal chicago:Edsger Dijkstra,,,Go To Statement Considered Harmful," +
     "Comm. of the ACM,1968",
   "(Dijkstra, 1968) Dijkstra, Edsger. \"Go To Statement" +
     " Considered Harmful.\" Comm. of the ACM (1968).\n",

   "direct print journal chicago:Edsger Dijkstra,Albert Einstein,,Go To Statement Considered Harmful," +
     "Comm. of the ACM,1968",
   "(Dijkstra, 1968) Dijkstra, Edsger, and Einstein, Albert. \"Go To Statement" +
     " Considered Harmful.\" Comm. of the ACM (1968).\n",

   "direct print journal chicago:Edsger Dijkstra,Albert Einstein,Harald Lesch,Go To Statement Considered Harmful," +
     "Comm. of the ACM,1968",
   "(Dijkstra, 1968) Dijkstra, Edsger, Einstein, Albert, and Lesch, Harald. \"Go To Statement" +
     " Considered Harmful.\" Comm. of the ACM (1968).\n",
     
     // invalid direct print journal
     "direct print journal chicago:Sergey Brin,Sergey Brin,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,1998",
   "Error, duplicate Author",

   "direct print journal chicago:Sergey Brin,Harald Lesch,Sergey Brin," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,Comm. of the ACM,1998",
   "Error, duplicate Author",

   /*"direct print journal chicago:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertex;tual Web Search" +
     " Engine,WWW,1998",
   "Error, wrong format of title",*/

   /*"direct print journal chicago:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WW;W,1998",
   "Error, wrong format of journal name",*/

   "direct print journal chicago:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,19j98",
   "Error, wrong format of year",

   "direct print journal ieee:Sergey Brin,Sergey Brin,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,1998",
   "Error, duplicate Author",

   "direct print journal ieee:Sergey Brin,Harald Lesch,Sergey Brin," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,Comm. of the ACM,1998",
   "Error, duplicate Author",

   /*"direct print journal ieee:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertex;tual Web Search" +
     " Engine,WWW,1998",
   "Error, wrong format of title",*/

   /*"direct print journal ieee:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WW;W,1998",
   "Error, wrong format of journal name",*/

   "direct print journal ieee:Sergey Brin,Lawrence Page,Harald Lesch," +
     "The Anatomy of a Large-Scale Hypertextual Web Search" +
     " Engine,WWW,199l8",
   "Error, wrong format of year",
   
   "direct print journal ieee:",
   "Error, wrong format / invalid input",
   
   "direct print journal chicago:",
   "Error, wrong format / invalid input",
     
   // additional data for bibliography
   "add article to series Informatik:article5,2017,title five", "Ok",
   "written-by article5,Albert Einstein;Niels Bohr", "Ok",
   "add author Mara,Curie", "Ok",
   "add article to series Informatik:article6,2017,title six", "Ok",
   "written-by article6,Albert Einstein;Mara Curie", "Ok",

   // print bibliography
   "print bibliography ieee:article1;article2;article3;article4;article5;article6",
   "[1] M. Curie, \"title two,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[2] A. Einstein, \"title one,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[3] A. Einstein and N. Bohr, \"title five,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[4] A. Einstein and M. Curie, \"title six,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[5] A. Einstein and M. Curie, \"title three,\" in Proceedings of Informatik, Karlsruhe, 2014.\n" +
     "[6] A. Einstein et al., \"title four,\" in Proceedings of Informatik, Karlsruhe, 2013.\n",
   "print bibliography chicago:article1;article2;article3;article4;article5;article6",
   "(Curie, 2017) Curie, Marie. \"title two.\" Paper presented at Informatik, 2017, Karlsruhe.\n" +
     "(Einstein, 2017) Einstein, Albert. \"title one.\" Paper presented at Informatik, 2017, Karlsruhe.\n" +
     "(Einstein, 2017) Einstein, Albert, and Bohr, Niels. \"title five.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, and Curie, Mara. \"title six.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2014) Einstein, Albert, and Curie, Marie. \"title three.\" Paper presented at Informatik, 2014, Karlsruhe.\n"
     + "(Einstein, 2013) Einstein, Albert, Curie, Marie, and Bohr, Niels. \"title four.\" Paper presented at Informatik, 2013, Karlsruhe.\n",

   // additional data for another bibliography
   "add conference Informatik,2018,Karlsruhe", "Ok",
   "add article to series Informatik:article7,2017,title six", "Ok",
   "written-by article7,Albert Einstein;Niels Bohr", "Ok",
   "add article to series Informatik:article8,2017,title six", "Ok",
   "written-by article8,Albert Einstein;Niels Bohr", "Ok",
   "add article to series Informatik:article9,2018,title six", "Ok",
   "written-by article9,Albert Einstein;Niels Bohr", "Ok",
   "add article to series Informatik:article10,2017,title seven", "Ok",
   "written-by article10,Albert Einstein;Niels Bohr", "Ok",

   // print the bibliography
   "print bibliography ieee:article1;article2;article3;article4;article5;article6;article7;article8;article9;article10",
   "[1] M. Curie, \"title two,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[2] A. Einstein, \"title one,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[3] A. Einstein and N. Bohr, \"title five,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[4] A. Einstein and N. Bohr, \"title seven,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[5] A. Einstein and N. Bohr, \"title six,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[6] A. Einstein and N. Bohr, \"title six,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[7] A. Einstein and N. Bohr, \"title six,\" in Proceedings of Informatik, Karlsruhe, 2018.\n" +
     "[8] A. Einstein and M. Curie, \"title six,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[9] A. Einstein and M. Curie, \"title three,\" in Proceedings of Informatik, Karlsruhe, 2014.\n" +
     "[10] A. Einstein et al., \"title four,\" in Proceedings of Informatik, Karlsruhe, 2013.\n",

   "print bibliography chicago:article1;article2;article3;article4;article5;article6;article7;article8;article9;article10",
   "(Curie, 2017) Curie, Marie. \"title two.\" Paper presented at Informatik, 2017, Karlsruhe.\n" +
     "(Einstein, 2017) Einstein, Albert. \"title one.\" Paper presented at Informatik, 2017, Karlsruhe.\n" +
     "(Einstein, 2017) Einstein, Albert, and Bohr, Niels. \"title five.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, and Bohr, Niels. \"title seven.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, and Bohr, Niels. \"title six.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, and Bohr, Niels. \"title six.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2018) Einstein, Albert, and Bohr, Niels. \"title six.\" Paper presented at Informatik, 2018, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, and Curie, Mara. \"title six.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2014) Einstein, Albert, and Curie, Marie. \"title three.\" Paper presented at Informatik, 2014, Karlsruhe.\n"
     + "(Einstein, 2013) Einstein, Albert, Curie, Marie, and Bohr, Niels. \"title four.\" Paper presented at Informatik, 2013, Karlsruhe.\n",

   // additional data for another bibliography
   "add article to series Informatik:article11,2017,title six", "Ok",
   "written-by article11,Albert Einstein;Niels Bohr;Marie Curie", "Ok",

   // print bibliography
   "print bibliography ieee:article1;article2;article3;article4;article5;article6;article7;article8;article9;article10;article11",
   "[1] M. Curie, \"title two,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[2] A. Einstein, \"title one,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[3] A. Einstein and N. Bohr, \"title five,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[4] A. Einstein and N. Bohr, \"title seven,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[5] A. Einstein and N. Bohr, \"title six,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[6] A. Einstein and N. Bohr, \"title six,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[7] A. Einstein and N. Bohr, \"title six,\" in Proceedings of Informatik, Karlsruhe, 2018.\n" +
     "[8] A. Einstein et al., \"title six,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[9] A. Einstein and M. Curie, \"title six,\" in Proceedings of Informatik, Karlsruhe, 2017.\n" +
     "[10] A. Einstein and M. Curie, \"title three,\" in Proceedings of Informatik, Karlsruhe, 2014.\n" +
     "[11] A. Einstein et al., \"title four,\" in Proceedings of Informatik, Karlsruhe, 2013.\n",

   "print bibliography chicago:article1;article2;article3;article4;article5;article6;article7;article8;article9;article10;article11",
   "(Curie, 2017) Curie, Marie. \"title two.\" Paper presented at Informatik, 2017, Karlsruhe.\n" +
     "(Einstein, 2017) Einstein, Albert. \"title one.\" Paper presented at Informatik, 2017, Karlsruhe.\n" +
     "(Einstein, 2017) Einstein, Albert, and Bohr, Niels. \"title five.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, and Bohr, Niels. \"title seven.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, and Bohr, Niels. \"title six.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, and Bohr, Niels. \"title six.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2018) Einstein, Albert, and Bohr, Niels. \"title six.\" Paper presented at Informatik, 2018, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, Bohr, Niels, and Curie, Marie. \"title six.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2017) Einstein, Albert, and Curie, Mara. \"title six.\" Paper presented at Informatik, 2017, Karlsruhe.\n"
     + "(Einstein, 2014) Einstein, Albert, and Curie, Marie. \"title three.\" Paper presented at Informatik, 2014, Karlsruhe.\n"
     + "(Einstein, 2013) Einstein, Albert, Curie, Marie, and Bohr, Niels. \"title four.\" Paper presented at Informatik, 2013, Karlsruhe.\n",

   // additions to the database to be able to use the given example
   "add author Shashi,Afolabi", "Ok",
   "add author Emiola,Lowry", "Ok",
   "add author Richard,Rhinelander", "Ok",
   "add journal TSE,IEEE", "Ok",
   "add conference series ICSA", "Ok",
   "add conference ICSA,2017,Gothenburg", "Ok",
   "add article to series ICSA:rr2017,2017,Components still have no interfaces", "Ok",
   "add article to journal TSE:mvp2015,2015,Model Consistency", "Ok",
   "add article to journal TSE:mvp2016,2016,Better Model Consistency", "Ok",
   "written-by rr2017,Richard Rhinelander", "Ok",
   "written-by mvp2016,Shashi Afolabi;Emiola Lowry", "Ok",
   "written-by mvp2015,Shashi Afolabi;Richard Rhinelander", "Ok",

   // print bibliography
   "print bibliography ieee:mvp2016;mvp2015;rr2017",
   "[1] S. Afolabi and E. Lowry, \"Better Model Consistency,\" TSE, 2016.\n" +
     "[2] S. Afolabi and R. Rhinelander, \"Model Consistency,\" TSE, 2015.\n" +
     "[3] R. Rhinelander, \"Components still have no interfaces,\" in Proceedings of ICSA, Gothenburg, 2017.\n",
   "print bibliography chicago:mvp2016;mvp2015;rr2017",
   "(Afolabi, 2016) Afolabi, Shashi, and Lowry, Emiola. \"Better Model Consistency.\" TSE (2016).\n" +
     "(Afolabi, 2015) Afolabi, Shashi, and Rhinelander, Richard. \"Model Consistency.\" TSE (2015).\n" +
     "(Rhinelander, 2017) Rhinelander, Richard. \"Components still have no interfaces.\" Paper presented at ICSA, 2017, Gothenburg.\n",

   // place to add more articles / cites etc.
   // invalid commands can be written in their
   // corresponding paragraph above

   // adding entities with double dots and print them
   // control they were added properly
   "add conference series :WI:TH:", "Ok",
   "add conference :WI:TH:,2017,:als:o:WI:TH:", "Ok",
   "add journal :Wi:th:,:WI:TH:", "Ok",
   "add article to series :WI:TH::informatikwithdoubledot,2017,:WI:TH:2017", "Ok",
   "add article to journal :Wi:th::informatikwithdoubledot2,2017,:WI:TH:2017", "Ok",
   "add keywords to series :WI:TH::doubledot", "Ok",
   "add keywords to journal :Wi:th::doubledot", "Ok",
   "add keywords to series :WI:TH::doubledot", "Ok",
   "list invalid publications", "#informatikwithdoubledot informatikwithdoubledot2",
   "written-by informatikwithdoubledot,Richard Rhinelander", "Ok",
   "written-by informatikwithdoubledot2,Richard Rhinelander", "Ok",
   "print bibliography chicago:informatikwithdoubledot;informatikwithdoubledot2",
   "(Rhinelander, 2017) Rhinelander, Richard. \":WI:TH:2017.\" Paper presented at :WI:TH:, 2017, :als:o:WI:TH:.\n" +
     "(Rhinelander, 2017) Rhinelander, Richard. \":WI:TH:2017.\" :Wi:th: (2017).\n",
   "cites informatikwithdoubledot,informatikwithdoubledot2", "Error, publication year has to be before",
   "all publications",
   "#informatik2017 informatik2016 informatik2015 relativitaetstheorie1999 relativitaetstheorie1998 "
     + "relativitaetstheorie1997 relativitaetstheorie1996 journal1firstarticle article1 article2 article3 article4 article5 "
     + "article6 article7 article8 article9 article10 article11 rr2017 mvp2015 mvp2016 informatikwithdoubledot informatikwithdoubledot2",
   "direct print journal ieee:Edsger Dijkstra,,,Go :To :Statement :Considered: Harmful," +
     "Comm. of the ACM,1968",
   "[1] E. Dijkstra, \"Go :To :Statement :Considered: Harmful" +
     ",\" Comm. of the ACM, 1968.\n",

   "quit", "Ok"

 };
 private static int count = 0;
 private static int wrongAnswers = 0;
 private static int rightAnswers = 0;

 // Determines the program output!!!!!!!!!
 private static boolean printAll = false;
 private static boolean printWrong = !printAll;
 private static boolean outputInFile = false;

 private static String output = "";
 private static long time = 0;

 public static String readLine() {
  if (outputInFile) {
   try {
    System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt", true)), true));
   } catch (FileNotFoundException e) {
    e.printStackTrace();
   }
  }
  if (count == 0) {
   time = (System.currentTimeMillis());
  }
  String lineStart = "--> ";
  if (count > 1) {
   if (printAll) {
    System.out.println(lineStart + (count - 2) + " " + code[count - 2]);
    System.out.println(lineStart + (count - 1) + " " + code[count - 1]);
    System.out.println(output);
   }
   if (code[count - 1].startsWith("Error")) {
    if (output.startsWith("Error, ")) {
     rightAnswers++;
    } else {
     wrongAnswers++;
     if (printWrong) {
      System.out.println(lineStart + (count - 2) + " " + code[count - 2]);
      System.out.println(lineStart + (count - 1) + " " + code[count - 1]);
      System.out.println(output);
     }
    }
   } else if (code[count - 1].equals("Ok")) {
    if (output.equals("Ok\n")) {
     rightAnswers++;
    } else {
     wrongAnswers++;
     if (printWrong) {
      System.out.println(lineStart + (count - 2) + " " + code[count - 2]);
      System.out.println(lineStart + (count - 1) + " " + code[count - 1]);
      System.out.println(output);
     }
    }
   } else if ((code[count - 1]).equals(output)) {
    rightAnswers++;
   } else if (code[count - 1].startsWith("#")) {
    String[] data = code[count - 1].substring(1, code[count - 1].length()).split(" ");
    boolean containsAll = true;
    String usedOutput = output.replaceAll("\n", "").replaceAll(" ", "");
    for (String datum : data) {
     if (!output.contains(datum)) {
      containsAll = false;
     }
     usedOutput = usedOutput.replaceFirst(datum, "");
    }

    if (containsAll && usedOutput.equals("")) {
     rightAnswers++;
    } else {
     wrongAnswers++;
     if (printWrong) {
      System.out.println(lineStart + (count - 2) + " " + code[count - 2]);
      System.out.println(lineStart + (count - 1) + " " + code[count - 1]);
      System.out.println(output);
     }
    }
   } else {
    wrongAnswers++;
    if (printWrong) {
     System.out.println(lineStart + (count - 2) + " " + code[count - 2]);
     System.out.println(lineStart + (count - 1) + " " + code[count - 1]);
     System.out.println(output);
    }
   }
  }
  output = "";
  count += 2;
  if (count > code.length - 1) {
   System.out.println("Correct Anwers: " + rightAnswers + " Wrong Anwers: " + wrongAnswers + " Time: "
     + (System.currentTimeMillis() - time));
  }
  return code[count - 2];
 }

 public static void printError(String message) {
  output += "Error, " + message + "\n";
 }

 public static void printLine(final Object object) {
  output += object + "\n";
 }
}
