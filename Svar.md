# Svar på spørsmål

I løpet av denne semesteroppgaven har jeg diskutert og utvekslet ideer til koden ilag med Åsmund Groven, Sanna Rosøy og Eiril Ugulen.
   
## Oppgave 1

1.1)

- Grid-klassene er viktig. De utgjør det helt grunnleggende i spillet, da en grid er nødvendig for å i det hele tatt ha et spill å spille. I grid finner vi forskjellige klasser som totalt utgjør spillbrettet, her er to eksempler: I Grid-klassen finnes det verdier som utgjør 2D-layouten til spillbrettet. GridDirection er et enum, som inneholder "final variables" som representerer de 8 ulike retningene en kan bevege seg i. 

- Player-klassene er også viktig, men ikke alle er like viktige. Dette basert på at ikke alle er like "nødvendige" for at spillet skal fungere. For å spille spillet er det nødvendig med player-klassene som utgjør det grunnleggende, altså to motparter som deltar. Da er det ikke nødvendig med GuiPlayer- klassen, da dette bare er en "oppgradering" fra å spille i for eksempel terminalen. En trenger heller ikke DumbPlayer-klassen. Klassene innad player som er viktig er f.eks AbstractPlayer, ConsolePlayer og PlayerList. 

- Klassene i game er også viktig. I klassene TicTacToe og ConnectFour er spillene implementert, som er nødvendig for å utgjøre alle handlingene og reglene i spillet, som er kodet i den abstrakte klassen Game. Vi finner også klassen GameBoard, som bestemmer om det er mulig å flytte spilleren til en spesifik location i grid. 

1.2)

- Abstraction blir brukt i to klasser, Game og AbstractPlayer. Her er målet å gjemme visse detaljer slik at en bare viser det mest nødvendige. I disse klassene er det ikke mulig å lage objekt. For å få tilgang, må det være arvet fra en annen klasse. 

- Encapsulation blir brukt i flere klasser. Her blir det brukt setters og getters for å samle kode og beskytte den fra å bli brukt av koden som er utenfor. Feks blir det laget en privat variabel som bare kan benyttes av public methods i klassen. Det blir videre brukt en get metode for å få access, som returnerer en den private variabelen. Dette skjer blant annet i Grid, IGrid, Game, GameBoard, ClickableGridListener, GamePanel, MainMenu og AbstractPlayer. 

- Inheritance/arv i Java er en metode som gjør det mulig å arve "attributes" og metoder fra en klasse til en annen. Arv deles inn i to kategorier, subclass (child) - klassen som arver fra den andre klassen, og superclass (parent) - klassen som blir arvet fra. For å arve en klasse, brukes "extends". Omtrent det samme skjer når en klasse skal ha tilgang til metoder i et interface, da blir det brukt "implements". 
IGrid<T> arver fra Iterable<T>. 
ConnectFour arver fra klassen Game. 
GameBoard arver fra Grid<Player>. 
TicTacToe arver fra Game. 
ClickableGrid arver fra JPanel. 
GamePanel arver fra JPanel.
ConsolePlayer arver fra AbstractPlayer.
DumbPlayer arver fra AbstractPlayer.
GameEndedException arver fra RuntimeException.
GuiPlayer arver fra AbstractPlayer.
MiniMaxPlayer arver fra AbstractPlayer.
RandomPlayer arver fra AbstractPlayer.
RestartException arver fra RuntimeException.

- Polymorphism brukes når vi har mange klasser som er relatert til hverandre gjennom arv. Polimorfisme er i dette tilfellet brukt i klasser som utvider game. Det er i hovedsak spillene; Othello, ConnectFour og TicTacToe. Polimorfisme blir også brukt siden GameBoard utvider grid. 

1.3) Spill som er enkle å lage som en utvidelse er feks "fem på rad", "seks på rad" osv. Det er veldig enkelt å gjøre, siden det kommer til å bruke samme regler og kode som spillene som er implementert, sett bort i fra hvor stor grid er og hvor mange brikker en må ha på rad. Andre spill på som er mulig å implementere kan være Minesweeper, basert på at vi allerede har en grid og to spillere som spiller mot hverandre. Dam er også et eksempel på et spill som burde være mulig å implementere. Spillet består av et 8x8 grid der målet er å komme over til motstanderen sin side ved å hoppe over brikkene. 

Spill som kan være likt men vanskeligere å implementere er spill som har en grid, men med mye mer kompliserte regler. Feks sjakk. F.eks vil spillet Dam være enklere å implementere enn Sjakk, siden reglene i Dam er enklere. I sjakk måtte en hatt mange flere klasser for å definere de forskjellige brikkene. 

1.4) SOLID består av fem prinsipp: Single Responsibility, Open/Closed, Liskov Substitution, Interfae Segregation og Dependency Inversion. 

**Single Responsibility** tar utgangspunkt i at en klasse skal ha en oppgave. Fordelen er at klassen vil være enklere å teste, den vil ha mindre funksjoner, som vil føre til færre avhengigheter, og den vil ha bedre organisering som gjør det lettere å feilsøke og finne frem i. Det blir brukt i flere klasser, alle steder hvor det blir utført en oppgave. Feks vil klassen TicTacToe ha en oppgave, som er å implementere TicTacToe, i forskjell til ConnectFour som har i oppgave å implementere ConnectFour. Om dette skjedde under en og samme klasse, ville det blitt rot. I Player finnes også eksempel, da det har blitt delt inn klasser for dei forskjellige spillertypene. Feks GuiPlayer, som har i mål om å bruke input fra GUI og ConsolePlayer, som har i mål om å lese fra terminal.

**Open/Closed** går ut på at en klasse skal være åpen for utvidelse, men samtidig lukket for modifikasjon. Det gjør at vi stopper oss sjøl eller andre fra å modifisere eksisterende kode som kan resultere i bugs. Isteden for å potensielt ødelegge kode, kan en bruke "extend" og arve fra en annen klasse som har kode vi kan bruke, utenom å ødelegge. Dette blir gjort flere steder, viser til tidligere svar om arv. 

**Liskov Substitution**  Hver underklasse skal kunne erstattes av foreldre klassen. Man kan utvide en klasse men man kan ikke stramme den mer inn. Feks game klassen, den blir utvidet men ikke strammet inn.

**interface Segregation** går ut på å dele et større interface inn i flere små interface slik at metodene vi arver blir mer "spesifike" for den oppgaven vi vil at arveklassen skal utføre. Feks vil et stort interface ha flere metoder som kan være unødvendige for klassene vi vil lage. Ved å lage flere spesifike Interface vil vi minimere dette. Dette blir ikke brukt så mye i filen, da det bare eksisterer tre interface som ikke er store nok til at det er poeng å dele de opp. 

**Dependency Inversion** 

## Oppgave 2

Planen for Othello er å starte med å extende Game klassen, slik at metodene som blir brukt i TicTacToe og ConnectFour, også kan bli brukt i Othello. Dette gjør det allerede enklere da en slipper å lage spillet fra grunn. Istedet skal en implementere metodene. 
Kommer til å ha som mål i starten å kunne plassere brikkene på brettet ved å bruke canPlace. Videre vil jeg utvikle programmet mer og mer. Mest sannsynlig vil jeg gå videre med å få til å flippe brikkene, før jeg lager en isWinner som forteller hvem som vinner og så gameOver som forteller når spillet er over. Deretter vil jeg sørge for at spillet fungerer i både GUI og terminal før jeg lager testene. Kommer til å prøve å lage tester til de fleste metodene.
I tillegg vil jeg lage et klassediagram der jeg tar utgangspunkt i det som allerede eksisterer, og så utvikle det etter hvert som metoden blir implementert.  

Starter med å lage to Othello konstruktører som skal plassere de første fire brikkene på brettet. I den øverste, "public Othello(Graphics graphics, Player p1, Player p2)",  blir det plassert brikker på brettet, til bruk i terminalen. Den andre konstruktøren, "public Othello(Graphics graphics, Iterable<Player> players)", er til GUI. 

Vil videre implementere metodene slik at Othello fungerer, disse metodene kommer til å være sentrale:

 - public boolean canPlace(Location loc): vil være en viktig funksjon for spillet, som vil avgjøre om det er mulig å plassere en brikke i en lokasjon, basert på om det ikke ligger en brikke på lokasjonen, om det vil bli flippet en spillbrikke etter plasseringen eller om det er lov å plassere brikken der. 

 - public List<Location> getPossibleMoves(): kommer til å bruke canPlace og lagre lokasjonene det er mulighet for å plassere i, i en listen. Metoden vil returnere listen med lokasjoner. 

 - public List<Location> getAffectedDisks(Location loc, GridDirection dir): kommer til å lage en liste med affected disks, som er brikkene som blir påvirket av valget til spilleren. 

 - public void flipDisks(Location currentLoc): metoden vil bruke en liste av affected disks og flippe de om listen ikke er tom. 

 - public void makeMove(Location loc): Den vil bruke canPlace til å vurdere om en kan gjøre en move. Om den kan gjøre en move, vil den sette den nye lokasjonen, flippe affected disks og så gå videre til neste spiller. 

 - public boolean gameOver(): en metode for å avgjøre når spillet er over. Det vil være om den ene spilleren har flere brikker enn den andre ved endt spill, enten når brettet er fullt eller når det ikke er mulig å gjøre flere moves. 

 I tillegg vil det være nødvendig å endre i klassen MainMenu slik at det er mulig å velge Othello i GUI. Det skjer i actionPerformed metoden. 

Klassediagrammet ligger i information mappen.

## Oppgave 3

Har denne gang kommentert i koden, men en mer ryddig løsning. Håper dette er bedre kommentert enn forrige gang.


Feil i koden: I TicTacToe spillet merker jeg at det er veldig mye enklere å slå AI i den nederste raden, enn de radene over i griden. Altså, spiller en spillet bare på (0,2), (1,2) eller (2,2) vil en slå AI hver eneste gang. Spiller en derimot høyere oppe i griden, vil det være veldig vanskelig å slå AI. Ser på dette som en feil i koden, der det er mulighet for forbedring. 

Noe som kunne vært forbedret er tiden det tar før AI tar et trekk. Den er så rask at det er vanskelig å se om den har gjort riktige trekk. Her skulle det vært et delay. Jeg har derfor sjekket med før og etter bilder gjennom et helt spill, og AI gjør riktige og lovlige trekk. 

Noe annet som har forbedringspotensiale er fargekodene i spillet. Å få til egen farge for Othello som ikke påvirket TicTacToe og ConnectFour hadde vært den beste løsningen. 




