# Ms_SafeSmartHome

Sammlung zweier eigenständiger Teilprojekte für ein sicheres Smart-Home-System:
einer Android-App zur Bedienung und einem JavaCard-Applet für die sichere
Schlüssel- bzw. Authentifizierungslogik auf einer Smartcard.

## Repository-Struktur

```
Ms_SafeSmartHome/
├── android-app/                 Android-App (Gradle, Modul :app)
├── javacard/JavaCardApplet/     JavaCard-Applet (JavaCard SDK, kein Gradle)
├── docs/                        (aktuell leer)
├── AGENTS.md                    Arbeitshinweise für Entwickler/Agenten
└── README.md
```

## Android-App (`android-app/`)

- Package/Namespace `com.example.safesmarthomeapp`, Einstiegspunkt `MainActivity`.
- UI: AppCompat + Material3, aktuell eine Hello-World-Standardansicht.
- Toolchain: Java 11 (Quellen), Gradle 9.6.0, AGP 9.5.1, compileSdk/targetSdk 37,
  minSdk 24. Der Gradle-Daemon läuft auf Java 25 (siehe
  `gradle/gradle-daemon-jvm.properties`).
- Abhängigkeiten zentral in `gradle/libs.versions.toml`; Repositories in
  `settings.gradle.kts`.

Befehle (immer aus `android-app/` ausführen):

```powershell
.\gradlew.bat assembleDebug         # Debug-APK bauen
.\gradlew.bat test                  # Unit-Tests
.\gradlew.bat connectedAndroidTest  # Instrumentierte Tests (Gerät/Emulator)
.\gradlew.bat lint                  # Lint
```

Voraussetzung: lokale Android-SDK-Installation; der Pfad steht in
`local.properties` (gitignoriert, nicht einchecken).

## JavaCard-Applet (`javacard/JavaCardApplet/`)

- JavaCard 3.0.5 (Classic API), Package `de.thowl.safesmarthome`, Applet
  `SmartHomeApplet`.
- Applet-AID/Package-AID und Build-Optionen stehen in
  `configurations/SmartHome.conf`.
- Build über den JavaCard-SDK-`converter` (kein Gradle/Maven, kein eingechecktes
  Build-Skript). Benötigt das JavaCard Development Kit inklusive
  `api_classic-3.0.5.jar` – ohne SDK ist kein Build möglich.

Details: siehe [`javacard/JavaCardApplet/README.md`](javacard/JavaCardApplet/README.md).

## Status

Beide Teilprojekte befinden sich im Grundgerüst-Zustand: Die Android-App zeigt eine
leere Standardansicht, das Applet beantwortet nur `SELECT` und lehnt alle anderen
APDUs mit `SW_INS_NOT_SUPPORTED` ab.

## Hinweise

- `build/`, `.gradle/`, `.idea/`, `*.iml`, `local.properties` und Keystores sind
  gitignoriert und sollten nicht eingecheckt werden.
- Code-Kommentare im JavaCard-Teil sind auf Deutsch.
