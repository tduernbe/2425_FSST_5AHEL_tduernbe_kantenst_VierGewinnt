Ich habe geschrieben:

Programmiere mir eine JAVAFX-Anwendung mit entsprechender Oberfläche, es soll das Spiel Vier Gewinnt werden, hier die Angabe, die Benutzeroberfläche ist wird später erstellt und hat den Namen "GameView.java", diese musst du nicht ausprogrammieren.


Erstellen Sie eine Java Anwendung welche nach dem Model-View-Controller Prinzip arbeitet - siehe https://www.tutorialspoint.com/design_pattern/mvc_pattern.htm
Spielregeln

    Auf einem Feld aus 7 Spalten und 6 Zeilen werfen zwei Spieler abwechselnd einen Stein in eine noch nicht belegte Spalte.
    Der Stein fällt nach unten bis er auf einen anderen Stein oder den Boden trifft.
    Sobald ein Spieler eine Reihe aus vier ununterbrochenen Steinen bildet (waagerecht, senkrecht oder diagonal), gewinnt er das Spiel.
    Kann kein Spieler vier Steine in eine Reihe bringen und sind alle Spalten belegt, so endet das Spiel unentschieden
Anforderungen

    Vor Start des Spieles werden die Namen der Spieler eingelesen.
    Spieler 1 wird durch ein o dargestellt, Spieler 2 durch ein x
    Vor jeder Eingabe der gewünschten Spaltennummer muss das Spielfeld mit dem aktuellen Spielstand dargestellt werden. Die Eingabe der gewünschten Spaltennummer hat mit Zahlen zu erfolgen. Die Eingabe muss solange wiederholt werden bis eine gültige Spaltennummer (im gültigen Bereich bzw. nicht volle Spalte) angegeben wurde.
    Nach jedem Spielzug muss geprüft werden, ob das Spiel gewonnen wurde. Ist dies der Fall, muss dem Gewinner des Spieles gratuliert werden.
    Außerdem muss geprüft werden, ob das Spiel unentschieden ausgegangen ist. Dies ist dann der Fall, wenn alle Spalten voll sind, und keiner der Spieler 4 Spielsteine in einer Reihe hat. Eine entsprechende Meldung muss ausgegeben werden.


Nächster prompt:

Das funktioniert super! bitte passe noch das Programm so an so das man seinen Namen eingeben kann und wenn jemand gewinnt eine Message erscheint und den Gewinner kürt. 


Nächster prompt:

Super, ich hätte noch  kleine Änderungen, bitte ändere das Spiel so ab, das man nicht bei 0 zu zählen beginnt sondern bei 1 und die Message funktioniert nichtso gut, kann man das eventuell mit einem Stage oder Scene machen, sodass die Message, die über den Gewinner informiert, die ganze Zeit bleibt?



An KI BOT gescheitert, fehlende Kompetenz 