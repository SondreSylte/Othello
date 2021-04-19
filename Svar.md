# Svar på spørsmål

*I denne filen legger du inn svar på spørsmålene som stilles i oppgaveteksten, eventuelt kommentarer og informasjon om kreative løsninger*

Feil i koden: I TicTacToe spillet merker jeg at det er veldig mye enklere å slå AI i den nederste raden, enn de radene over i griden. Altså, spiller en spillet bare på (0,2), (1,2) eller (2,2) vil en slå AI hver eneste gang. Spiller en derimot høyere oppe i griden, vil det være veldig vanskelig å slå AI. Ser på dette som en feil i koden, der det er mulighet for forbedring. 
   
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

- Polymorphism brukes når vi har mange klasser som er relatert til hverandre gjennom arv. 

1.3) Spill som er enkle å lage som en utvidelse er feks "fem på rad", "seks på rad" osv. Det er veldig enkelt å gjøre, siden det kommer til å bruke samme regler og kode som spillene som er implementert, sett bort i fra hvor stor grid er og hvor mange brikker en må ha på rad. Andre spill på som er mulig å implementere kan være Minesweeper, basert på at vi allerede har en grid og to spillere som spiller mot hverandre. Dam er også et eksempel på et spill som burde være mulig å implementere. Spillet består av et 8x8 grid der målet er å komme over til motstanderen sin side ved å hoppe over brikkene. 

Spill som kan være likt men vanskeligere å implementere er spill som har en grid, men med mye mer kompliserte regler. Feks sjakk. F.eks vil spillet Dam være enklere å implementere enn Sjakk, siden reglene i Dam er enklere. I sjakk måtte en hatt mange flere klasser for å definere de forskjellige brikkene. 

1.4) SOLID består av fem prinsipp: Single Responsibility, Open/Closed, Liskov Substitution, Interfae Segefation og Dependency Inversion. 
    - 


## Oppgave 2
(skriv svar her)

## Oppgave 3
(skriv svar her)

