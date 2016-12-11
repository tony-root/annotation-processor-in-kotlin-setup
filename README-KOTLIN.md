# Sample annotation processor written in Kotlin and attached to Android project

This might be a starting point for your annotation processing experiments on Android.

Android project can be both in Java and Kotlin, just use

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

When editing the processor, default compilation has some troubles detecting changes in the processor and does not regenerate the sources.
You might want to use this command while playing with your processor

```
./gradlew :app:cleanCompileDebugJavaWithJavac :app:compileDebugJavaWithJavac
```
