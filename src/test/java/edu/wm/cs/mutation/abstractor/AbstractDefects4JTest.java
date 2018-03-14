package edu.wm.cs.mutation.abstractor;

import edu.wm.cs.mutation.extractor.Defects4JInput;
import edu.wm.cs.mutation.extractor.MethodExtractor;
import edu.wm.cs.mutation.io.IOHandler;

import java.util.List;

public class AbstractDefects4JTest {
    public static void main(String[] args) {

        String dataPath = "data/";

        //Chart
        String projBasePath = dataPath + "Chart/";
        String outBasePath = dataPath + "out/Chart/";
        String modelConfigPath = dataPath + "spoonModel/model/Chart.json";
        String libPath = dataPath + "spoonModel/lib/Chart";
        boolean compiled = true;

        //Idiom path
        String idiomPath = dataPath + "idioms.csv";

        List<Defects4JInput> inputs = MethodExtractor.generateDefect4JInputs(projBasePath, outBasePath, modelConfigPath);
        for (Defects4JInput input : inputs) {
            MethodExtractor.extractFromDefects4J(input, libPath, compiled);
            MethodAbstractor.abstractMethods(MethodExtractor.getRawMethodsMap(), idiomPath);

            IOHandler.writeMethods(input.getOutPath(), MethodExtractor.getRawMethodsMap(), false);
            IOHandler.writeMethods(input.getOutPath(), MethodAbstractor.getAbstractedMethods(), true);
            IOHandler.writeMappings(input.getOutPath(), MethodAbstractor.getMappings());
        }
    }

}
