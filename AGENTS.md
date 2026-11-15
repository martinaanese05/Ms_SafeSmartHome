# AGENTS.md

## Repository layout

Two independent projects with no unified build:

- `android-app/` — Android Gradle project (root `settings.gradle.kts`, single `:app` module).
- `javacard/JavaCardApplet/` — JavaCard applet (IntelliJ module, built with the JavaCard SDK converter, not Gradle). Has its own `AGENTS.md` and `README.md`.
- `docs/` is empty.
- Root `README.md` is the project overview (structure, build commands, current status); keep it in sync when project setup changes.

## Android (`android-app/`)

Run all Gradle commands from `android-app/`, not the repo root:

- Build debug APK: `.\gradlew.bat assembleDebug`
- Unit tests: `.\gradlew.bat test`
- Single unit test: `.\gradlew.bat test --tests "com.example.safesmarthomeapp.ExampleUnitTest.addition_isCorrect"`
- Instrumented tests (device/emulator required): `.\gradlew.bat connectedAndroidTest`
- Lint: `.\gradlew.bat lint`

Notes:

- Use the committed wrapper (Gradle 9.6.0, AGP 9.5.1), not a system Gradle.
- Dependency versions live in `gradle/libs.versions.toml` (version catalog); add libraries there and reference via `libs.*`.
- Repositories are centralized in `settings.gradle.kts` with `RepositoriesMode.FAIL_ON_PROJECT_REPOS`; add repositories there, not in module build files.
- Toolchain: sources compile at Java 11, but Gradle pins its daemon to Java 25 via `gradle/gradle-daemon-jvm.properties` (may download a JDK).
- Build file uses AGP 9.x DSL (`compileSdk { version = release(37) }`, `optimization { ... }`); keep that style instead of older template syntax.
- `local.properties` holds the local Android SDK path and is gitignored; never commit it.
- Package/namespace is `com.example.safesmarthomeapp`, entry point `MainActivity`, UI is AppCompat + Material3.

## JavaCard (`javacard/JavaCardApplet/`)

- Source: `src/`, package `de.thowl.safesmarthome`; applet AID is defined in `configurations/SmartHome.conf`.
- Do NOT compile with plain `javac`: it targets JavaCard 3.0.5 (`-target 3.0.5`) and needs the JavaCard 3.0.5 classic API (`javacard.framework.*`), which is not in the JDK.
- The JavaCard SDK/API jar is not vendored. The IntelliJ module expects it at `../../../../jars/javacard/lib/api_classic-3.0.5.jar` (outside this repo), so a fresh environment cannot build this project without installing the SDK.
- No build script is checked in. `configurations/SmartHome.conf` is input to the JavaCard SDK `converter`, which emits CAP/JCA/EXP to `build/deliverables/SmartHome`.
- Existing applet comments are German; match that style.
- Do not mix this package/AID with the Android app's `com.example.safesmarthomeapp`.

## Git

- `javacard/` is currently untracked (only `android-app/` and root files are committed), so `git diff` will not show JavaCard edits.
- Gitignored: `**/build/`, `.gradle/`, `.idea/`, `*iml`, `local.properties`, `*.jks`, `*.keystore`; never add them.
