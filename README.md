# Sample annotation processor setup

The project consists of two hello-world annotation processors attached to Android app. One processor is written in **Java**, the other one is in **Kotlin**.

This might be a starting point for your annotation processing experiments on Android.

## Usage

Run

```
./gradlew clean assembleDebug
```

When editing the processor, default incremental compilation has some troubles detecting changes in the processor and does not regenerate the sources.
You might want to use this command while playing with your processor

```
./gradlew :app:cleanCompileDebugJavaWithJavac :app:compileDebugJavaWithJavac
```

## Android sample

Android project can be both in Java and Kotlin,   

just use

```
dependencies {
    annotationProcessor project(":processor-compiler")
}
```
for Java,    

or

```
dependencies {
    kapt project(":processor-compiler")
}
```
for Kotlin project.

## PRs and suggestions are welcome!
