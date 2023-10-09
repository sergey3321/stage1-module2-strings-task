package com.epam.mjc;

import javax.security.auth.login.Configuration;
import java.io.IOException;
import com.epam.mjc.MethodSignature;
import java.util.ArrayList;
import java.util.List;


public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */

    public MethodSignature parseFunction(String signatureString) {

        String[] partsTypeAndName = signatureString.split("\\(");
        String[] firstParts = partsTypeAndName[0].split(" ");

        String accessModifier = null;
        String returnType;
        String methodName;

        if (firstParts.length == 3) {
            accessModifier = firstParts[0];
            returnType = firstParts[1];
            methodName = firstParts[2];
        } else if (firstParts.length == 2) {
            returnType = firstParts[0];
            methodName = firstParts[1];
        } else {
            throw new IllegalArgumentException("Invalid method signature");
        }

        List<MethodSignature.Argument> argumentList = new ArrayList<>();

        if (partsTypeAndName.length > 1) {
            String argumentsString = partsTypeAndName[1].substring(0, partsTypeAndName[1].length() - 1);
            if (!argumentsString.isEmpty()) {
                String[] arguments = argumentsString.split(", ");

                for (String argument : arguments) {
                    String[] argumentParts = argument.split(" ");
                    argumentList.add(new MethodSignature.Argument(argumentParts[0], argumentParts[1]));
                }
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, argumentList);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}

