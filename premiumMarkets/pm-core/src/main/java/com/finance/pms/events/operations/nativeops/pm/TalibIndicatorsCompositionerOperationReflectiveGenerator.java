package com.finance.pms.events.operations.nativeops.pm;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.TalibIndicatorsCompositioner;

@Service("talibIndicatorsCompositionerOperationReflectiveGenerator")
public class TalibIndicatorsCompositionerOperationReflectiveGenerator {

    private static MyLogger LOGGER = MyLogger.getLogger(TalibIndicatorsCompositionerOperationReflectiveGenerator.class);

    public Map<String, TalibIndicatorsCompositionerGenericOperation> generate() throws IOException {

        Set<Class<? extends TalibIndicatorsCompositioner>> compositionCalculatorsClasses = getClassesUsingReflexions("com.finance.pms.events.calculation");
        Map<String, TalibIndicatorsCompositionerGenericOperation> compositionCalculatorOperations = compositionCalculatorsClasses.stream()
                .filter(calculatorClass -> !Modifier.isAbstract( calculatorClass.getModifiers()))
                .map(calculatorClass -> {

                    try {

                        List<String> inConstantsNames = Arrays.stream(calculatorClass.getDeclaredMethods())
                                .filter(m -> m.getName().equals("init"))
                                .flatMap(m -> Arrays.stream(m.getParameters()).map( p -> p.getName()))
                                .collect(Collectors.toList());

                        LOGGER.info("Initialising TalibIndicatorsCompositionerGenericOperation : "+calculatorClass.getSimpleName());
                        return new TalibIndicatorsCompositionerGenericOperation(
                                "gx_"+calculatorClass.getSimpleName(), "gx_ "+calculatorClass.getSimpleName(), 
                                calculatorClass, inConstantsNames);

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
}
