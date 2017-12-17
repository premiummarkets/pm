package com.finance.pms.events.operations.nativeops.pm;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.calculation.EventDefDescriptorStatic;
import com.finance.pms.events.calculation.TalibIndicatorsCompositioner;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;

@Service("talibIndicatorsCompositionerOperationReflectiveGenerator")
public class TalibIndicatorsCompositionerOperationReflectiveGenerator {

    private static MyLogger LOGGER = MyLogger.getLogger(TalibIndicatorsCompositionerOperationReflectiveGenerator.class);

//    @SuppressWarnings("unchecked")
    public Map<String, TalibIndicatorsCompositionerGenericOperation> generate() throws IOException {

//        Class<?>[] packageClasses = getClasses("com.finance.pms.events.calculation");
//        Set<Class<? extends TalibIndicatorsCompositioner>> compositionCalculatorsClasses = new HashSet<>();
//        Arrays.stream(packageClasses).forEach(compositionCalcClass -> {
//            if (!Modifier.isAbstract(compositionCalcClass.getModifiers()) && TalibIndicatorsCompositioner.class.isAssignableFrom(compositionCalcClass)) {
//                LOGGER.info("Found potential TalibIndicatorsCompositionerGenericOperation : "+compositionCalcClass.getSimpleName());
//                compositionCalculatorsClasses.add((Class<? extends TalibIndicatorsCompositioner>) compositionCalcClass);
//            }
//        });

        Set<Class<? extends TalibIndicatorsCompositioner>> compositionCalculatorsClasses = getClassesUsingReflexions("com.finance.pms.events.calculation");
        Map<String, TalibIndicatorsCompositionerGenericOperation> compositionCalculatorOperations = compositionCalculatorsClasses.stream()
                .filter(calculatorClass -> !Modifier.isAbstract( calculatorClass.getModifiers()))
                .map(calculatorClass -> {

                    try {

                        TalibIndicatorsCompositioner calculator = calculatorClass.getConstructor().newInstance();
                        EventDefinition eventDefinition = (EventDefinition) calculator.getEventDefinition();
                        EventDefDescriptorStatic eventDefDescriptor = eventDefinition.getEventDefDescriptor();
                        if (eventDefDescriptor == null) {
                            throw new NotImplementedException("No static descriptor implemented for "+eventDefinition+". This operation compositioner won't be reflected. Please implement.");
                        }
                        LinkedHashMap<String, Type> outputSelectorArray = eventDefDescriptor.descriptionMap();

                        List<String> inConstantsNames = Arrays.stream(calculatorClass.getDeclaredMethods())
                                .filter(m -> m.getName().equals("init"))
                                .flatMap(m -> Arrays.stream(m.getParameters()).map( p -> p.getName()))
                                .collect(Collectors.toList());

                        LOGGER.info("Initialising TalibIndicatorsCompositionerGenericOperation : "+calculatorClass.getSimpleName());
                        return new TalibIndicatorsCompositionerGenericOperation(
                                "gx_"+calculatorClass.getSimpleName(), 
                                "gx_ "+calculatorClass.getSimpleName()+", "+eventDefinition.getEventReadableDef(), 
                                calculator,
                                inConstantsNames, outputSelectorArray);

                    } catch (NotImplementedException e) {
                        LOGGER.warn(e.getMessage());
                        return null;
                    } catch (Exception e) {
                        LOGGER.error(calculatorClass.getSimpleName(), e);
                        return null;
                    }

                })
                .filter(ccOp -> ccOp != null)
                .collect(Collectors.toMap(ccOp -> ccOp.getReference(), ccOp -> ccOp));

        LOGGER.info("List of TalibIndicatorsCompositionerGenericOperations : "+compositionCalculatorOperations);
        return compositionCalculatorOperations;
    }
    
    private Set<Class<? extends TalibIndicatorsCompositioner>> getClassesUsingReflexions(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends TalibIndicatorsCompositioner>> subTypes = reflections.getSubTypesOf(TalibIndicatorsCompositioner.class);
        return subTypes;
    }

//    private Class<?>[] getClasses(String packageName) throws IOException {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        assert classLoader != null;
//        String path = packageName.replace('.', '/');
//        Enumeration<URL> resources = classLoader.getResources(path);
//        List<File> dirs = new ArrayList<File>();
//        while (resources.hasMoreElements()) {
//            URL resource = resources.nextElement();
//            dirs.add(new File(resource.getFile()));
//        }
//        ArrayList<Class<?>> classes = new ArrayList<>();
//        for (File directory : dirs) {
//            classes.addAll(findClasses(directory, packageName));
//        }
//        return classes.toArray(new Class[classes.size()]);
//    }
//
//    private List<Class<?>> findClasses(File directory, String packageName) {
//        List<Class<?>> classes = new ArrayList<>();
//        if (!directory.exists()) {
//            return classes;
//        }
//        File[] files = directory.listFiles();
//        for (File file : files) {
//            if (file.isDirectory()) {
//                assert !file.getName().contains(".");
//                classes.addAll(findClasses(file, packageName + "." + file.getName()));
//            } else if (file.getName().endsWith(".class")) {
//                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
//                try {
//                    classes.add((Class<?>) Class.forName(className));
//                } catch (ClassNotFoundException e) {
//                    LOGGER.error("Can't find class : "+className, e);
//                }
//            }
//        }
//        return classes;
//    }

}
