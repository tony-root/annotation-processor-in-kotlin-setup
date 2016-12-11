package com.rutkevich.processor

import com.rutkevich.processorruntime.ExampleAnnotation
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import java.io.IOException
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic

class ExampleProcessorInKotlin : AbstractProcessor() {

    private lateinit var typeUtils: Types
    private lateinit var elementUtils: Elements
    private lateinit var filer: Filer
    private lateinit var messager: Messager

    private var created = false

    @Synchronized override fun init(env: ProcessingEnvironment) {
        typeUtils = env.typeUtils
        elementUtils = env.elementUtils
        filer = env.filer
        messager = env.messager

        messager.printMessage(Diagnostic.Kind.NOTE, "Initialized in Kotlin")
    }

    override fun process(annotations: Set<TypeElement>, env: RoundEnvironment): Boolean {
        messager.printMessage(Diagnostic.Kind.NOTE, "Processing in Kotlin ...")

        if (!created) {
            writeFile()
            created = true
        }

        return true
    }

    private fun writeFile() {
        val main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Void.TYPE)
                .addParameter(Array<String>::class.java, "args")
                .addStatement("\$T.out.println(\$S)", System::class.java, "Hello, JavaPoet on Android!")
                .build()

        val helloWorld = TypeSpec.classBuilder("GeneratedByProcessorInKotlin")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build()

        val javaFile = JavaFile.builder("com.rutkevich.processorsample", helloWorld)
                .build()

        try {
            javaFile.writeTo(filer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getSupportedAnnotationTypes() = setOf(ExampleAnnotation::class.java.getCanonicalName())

    override fun getSupportedSourceVersion() = SourceVersion.RELEASE_7

}
