# EasyPlay

EasyPlay was a **course final project** centered on a **Twitter/X–oriented music player and manager**: the idea was to combine **music playback and playlists** with **social sharing and interaction** around tracks (posting, discovering, or organizing music in a Twitter/X–style workflow).  

**What is in this repository:** a **Java prototype** of the playback side—load **MP3** files from a directory into a playlist, play in **order** or **shuffle**, and drive playback from a **simple console loop** (`PlayerTest`). There is **no Twitter/X SDK, HTTP client, or similar integration in the source tree here**; that layer was part of the project concept rather than this checked-in snapshot.

## Project context

Collaborative **academic** work by **three students**, submitted as the **final project** for a **technical programming** course. The goal was learning-oriented (design, threading, media handling), not a shipping product.

## Tech stack

- **Java** (`EasyPlay_12_9/` package layout: `entities`, `loader`, `enumerations`, `main`)
- **JLayer** (`javazoom.jl.player`) — MP3 decoding/playback in `EasyPlayer`
- **Java standard library** — files, threads, `Scanner` for console input
- **`MediaPlayerDemo.java`** — separate **Swing** demo using **Java Media Framework** (JMF); comment in file notes JMF must be installed for that demo

## Running the project

There is **no** Maven/Gradle file or vendored JAR in this snapshot. To try the main prototype:

1. Add **JL/MP3 (JLayer)** to the classpath so `import javazoom.jl.player.*` resolves.
2. Open `EasyPlay_12_9/main/PlayerTest.java` and **replace the hard-coded music folder path** (it currently points at a developer-specific Windows path).
3. Compile the `EasyPlay_12_9` sources with correct package structure and run `main.PlayerTest`.

`MediaPlayerDemo.java` is standalone; follow JMF setup if that file is run.

## Status

**Archived — not maintained.** Preserved as submitted coursework; expect rough edges, deprecated APIs (e.g. `Thread.suspend`/`resume` in `PlayerTest`), and paths tied to the original development machine.
