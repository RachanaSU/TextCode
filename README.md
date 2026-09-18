# TextCode

An Android app that encrypts and decrypts plain text using a user-supplied secret key. Built in Java as an academic project — team lead, 3-person team. Scored 99/100.

## How it works

`TextCodeCipher.java` implements a custom, key-derived text-transformation scheme (not a standard algorithm like AES or DES):

1. The secret key is converted to bytes and repeated to match the length of the message (a repeating keystream).
2. Each byte of the message is combined with the corresponding keystream byte using XOR.
3. The result is Base64-encoded so it can be safely displayed, copied, or sent as plain text.

Decryption reverses this: Base64-decode, then XOR against the same repeating keystream to recover the original message. The same secret key has to be entered on both ends — whoever holds the key can decrypt the message, and no one else can, so every encoded message is unique to that sender/receiver key pair.

This was built to be simple and readable, not cryptographically strong — it's a learning project exploring how a shared secret can drive a reversible transformation, not production-grade encryption.

## Project structure

```
TextCode/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/rachana/textcode/
│       │   ├── MainActivity.java
│       │   └── TextCodeCipher.java
│       └── res/
│           ├── layout/activity_main.xml
│           └── values/ (strings.xml, colors.xml, themes.xml)
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Running it

Open the `TextCode` folder in Android Studio, let Gradle sync, and run on an emulator or device (minSdk 21).

## Pushing to GitHub

From inside the `TextCode` folder:

```bash
git init
git add .
git commit -m "Initial commit: TextCode Android app"
git branch -M main
git remote add origin https://github.com/RachanaSU/TextCode.git
git push -u origin main
```

(Create the empty `TextCode` repo on github.com first, under your RachanaSU account, before running the last two commands.)
