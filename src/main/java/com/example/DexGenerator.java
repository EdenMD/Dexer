package com.example;

import com.android.dx.Code;
import com.android.dx.DexMaker;
import com.android.dx.MethodId;
import com.android.dx.TypeId;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DexGenerator {

    public static void main(String[] args) throws IOException {
        //----start of generated_class_definition----
        // Define the TypeId for the class we want to generate
        TypeId<?> generatedType = TypeId.get("Lcom/example/GeneratedClass;");

        // Create a new DexMaker instance
        DexMaker dexMaker = new DexMaker();

        // Declare the class: public final class com.example.GeneratedClass
        dexMaker.declare(generatedType, "Ljava/lang/Object;", "GeneratedClass.java",
                new String[0]); // No interfaces

        // Add a public static method: public static String sayHello()
        MethodId<?, String> sayHello = generatedType.getStaticMethod(TypeId.get(String.class), "sayHello", new TypeId<?>[0]);

        // Implement the sayHello method
        Code code = dexMaker.declare(sayHello, 10, new String[0]);
        // Push the string "Hello from Dexmaker!" onto the stack
        int helloLocal = code.newLocal(TypeId.get(String.class));
        code.loadConstant(helloLocal, "Hello from Dexmaker!");
        // Return the string
        code.returnValue(helloLocal);
        //----end of generated_class_definition----

        // Define the output file for the DEX bytecode
        File outputDir = new File("build/dex");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        File outputFile = new File(outputDir, "output.dex");

        // Generate the DEX bytecode and write it to the file
        FileOutputStream fos = new FileOutputStream(outputFile);
        dexMaker.writeTo(fos);
        fos.close();

        System.out.println("Generated DEX file: " + outputFile.getAbsolutePath());
        System.out.println("It contains a class 'com.example.GeneratedClass' with a static method 'sayHello()'");
    }
}