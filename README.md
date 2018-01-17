# PureFunctionPasswordGenerator
An Android application to generate passwords in a predictable manner: for a given prefix and a site URL it should return the same password.
Since the application requests 0 permissions, you can be sure your private data remains only yours :-)

# Build

To build create file `local.properties` in the project root and set the `sdk.dir` property in it like:
```
sdk.dir=C\:\\dv\\tools\\android-adt\\sdk
```
And then run
```
$ ./gradlew assembleDebug
```
