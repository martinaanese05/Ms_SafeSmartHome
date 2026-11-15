# SafeSmartHome – JavaCard Applet

JavaCard-Applet für das SafeSmartHome-Projekt. Das Applet läuft auf einer JavaCard
(JavaCard 3.0.5, Classic API) und wird als CAP-Datei auf die Karte geladen.

## Überblick

| Eigenschaft | Wert |
| --- | --- |
| Package | `de.thowl.safesmarthome` |
| Applet-Klasse | `de.thowl.safesmarthome.SmartHomeApplet` |
| Applet-AID | `A0 00 00 00 62 03 01 0C 02 01` |
| Package-AID | `A0 00 00 00 62 03 01 0C 02` (Version 1.0) |
| Zielversion | JavaCard 3.0.5 |
| Build-System | JavaCard SDK `converter` (kein Gradle/Maven) |

## Verzeichnisstruktur

```
JavaCardApplet/
├── src/de/thowl/safesmarthome/
│   └── SmartHomeApplet.java      Applet-Quellcode
├── configurations/
│   └── SmartHome.conf            Konfiguration für den JavaCard-Converter
├── build/
│   ├── classes/                  Kompilierte Klassen (generiert, ignoriert)
│   └── deliverables/SmartHome/   CAP/JCA/EXP-Ausgabe (generiert, ignoriert)
├── JavaCardApplet.iml            IntelliJ-Modul (referenziert JavaCard-API)
└── .idea/                        IntelliJ-Projekteinstellungen (ignoriert)
```

## Voraussetzungen

- JavaCard Development Kit 3.0.5 inkl. `converter`
- JavaCard Classic API: `api_classic-3.0.5.jar`
  (vom IntelliJ-Modul relativ zum Modulverzeichnis erwartet unter
  `../../../../jars/javacard/lib/` – der aufgelöste Pfad hängt von der Lage des
  Repositories ab)
- JDK (das Modul nutzt `openjdk-25`)
- IntelliJ IDEA für die Entwicklung (das Modul ist als IntelliJ-Modul eingerichtet)

Ohne die JavaCard-API-JAR lässt sich das Projekt nicht kompilieren. Der Quellcode
lässt sich **nicht** mit einem einfachen `javac` ohne diese API bauen, da
`javacard.framework.*` nicht Teil des JDK ist.

## Build

Der Build erfolgt über den JavaCard-SDK-Converter mit der Konfigurationsdatei
`configurations/SmartHome.conf`. Die Datei steuert:

- Klassenverzeichnis: `build/classes`
- Applet-Registrierung inkl. AID
- Ausgabeformate: `CAP`, `JCA`, `EXP`
- Ausgabeverzeichnis: `build/deliverables/SmartHome`
- Zielversion: `3.0.5`

Ablauf:

1. Quellcode aus `src/` gegen die JavaCard-API kompilieren, Klassen nach
   `build/classes` schreiben.
2. Converter mit `configurations/SmartHome.conf` ausführen.

Ergebnis:

```
build/deliverables/SmartHome/de/thowl/safesmarthome/javacard/safesmarthome.cap
```

Die `.cap`-Datei wird anschließend mit den JavaCard-Werkzeugen (`apdutool`,
`scriptgen`, `gp` o. Ä.) auf die Karte installiert.

Es ist kein Build-Skript eingecheckt; der Converter wird über die JavaCard-SDK-
Werkzeuge bzw. die IntelliJ-Integration gestartet.

## APDU-Verarbeitung

`SmartHomeApplet.process()` behandelt aktuell nur `SELECT` (implizit über
`selectingApplet()`) und wirft für alle anderen Kommandos
`ISO7816.SW_INS_NOT_SUPPORTED` (`0x6D00`). Neue Kommandos werden im `switch`
über das `INS`-Byte ergänzt.

## Hinweise

- `build/` und `.idea/` sind gitignoriert und sollten nicht eingecheckt werden.
- Code-Kommentare sind auf Deutsch; dieser Stil wird beibehalten.
- Package/AID nicht mit der Android-App (`com.example.safesmarthomeapp`) vermischen.
