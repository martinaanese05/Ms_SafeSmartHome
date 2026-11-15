# AGENTS.md – JavaCardApplet

Gilt nur für `javacard/JavaCardApplet/`. Hinweise für das Gesamtprojekt stehen in
der `AGENTS.md` im Repository-Root.

## Projekt

- JavaCard-Applet (JavaCard 3.0.5, Classic API), Package `de.thowl.safesmarthome`.
- Einstiegspunkt: `src/de/thowl/safesmarthome/SmartHomeApplet.java`.
- Applet-AID/Package-AID und Build-Optionen stehen in `configurations/SmartHome.conf`.

## Build

- Kein Gradle/Maven und kein eingechecktes Build-Skript. Build über den
  JavaCard-SDK-`converter` mit `configurations/SmartHome.conf`.
- Quellen nach `build/classes` kompilieren, dann Converter ausführen; Ausgabe
  landet in `build/deliverables/SmartHome`.
- Nicht mit einfachem `javac` bauen: `javacard.framework.*` fehlt im JDK. Nötig ist
  `api_classic-3.0.5.jar`, die das IntelliJ-Modul unter
  `../../../../jars/javacard/lib/` erwartet (außerhalb dieses Repos).
- Fehlt die JavaCard-API, ist kein Build möglich – nicht versuchen, sie durch
  JDK-Klassen zu ersetzen.

## Konventionen

- Code-Kommentare auf Deutsch, passend zum bestehenden Applet.
- Package/AID nicht mit der Android-App (`com.example.safesmarthomeapp`) mischen.
- `build/`, `.idea/`, `*.iml` nicht einchecken.

## Verifikation

- Es gibt keine automatisierten Tests. Prüfung erfolgt durch erfolgreichen
  Converter-Lauf und erzeugte `.cap` in `build/deliverables/SmartHome`.
