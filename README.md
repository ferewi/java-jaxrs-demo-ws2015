## Anwendung Kompilieren

```bash
$ mkdir out/app
$ cd out/app
$ javac -d . -cp .:../../lib/* ../../src/*.java ../../src/*/*/*.java
```

## Anwendung Starten
Im Verzeichnis out/app
```bash
$ java -cp .:../../lib/* Server
```