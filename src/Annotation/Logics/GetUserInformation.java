package Annotation.Logics;

import Annotation.InjectionInfo;
import Annotation.RuntimeDescriptor;

/**
 * Stream(1) Function(2) GetSystemInformation
 */
public class GetUserInformation extends LogicBase {
    @Override
    @RuntimeDescriptor("GetUserInformation#doAction")
    @InjectionInfo(stream = 1, function = 2)
    public boolean doAction(Object[] params) {
        super.doAction(params);

        System.out.println("Start GetUserInformation.doAction()");
        System.out.println("Finish GetUserInformation.doAction()");

        return false;
    }
}
