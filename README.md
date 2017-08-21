This is a command line app built in Java.
Currently supported languages are English ('en') and Italian ('it').

To build, use:
```
gradle clean
gradle fatJar
```

To run, use `java -jar build/libs/pokemanager-1.0-SNAPSHOT.jar LANGUAGE`
passing in either 'en' or 'it' as the LANGUAGE.


I've provided some scripts for convenience:

To build, use:
```
sh build.sh
```

To run, use:
```
sh run.sh LANGUAGE
```
