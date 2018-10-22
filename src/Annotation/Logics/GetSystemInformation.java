package Annotation.Logics;

import Annotation.InjectionInfo;
import Annotation.RuntimeDescriptor;

/**
 * Stream(1) Function(1) GetSystemInformation
 */
public class GetSystemInformation extends LogicBase {
    @Override
    @RuntimeDescriptor("GetSystemInformation#doAction")
    @InjectionInfo(stream = 1, function = 1)
    public boolean doAction(Object[] params) {
        super.doAction(params);

        System.out.println("Start GetSystemInformation.doAction()");
        System.out.println("Finish GetSystemInformation.doAction()");

        return false;
    }
}
