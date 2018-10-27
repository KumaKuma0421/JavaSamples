package Annotation.Logics;

import Annotation.InjectionInfo;
import Annotation.RuntimeDescriptor;

/**
 * Stream(1) Function(1) GetSystemInformation
 */
public class GetSystemInformation extends LogicBase {
    @Override
    @RuntimeDescriptor("GetSystemInformation#preAction")
    public boolean preAction() {
        super.preAction();

        System.out.println("******preAction2******");

        return true;
    }

    @Override
    @RuntimeDescriptor("GetSystemInformation#doAction")
    @InjectionInfo(stream = 1, function = 1)
    public boolean doAction(Object[] params) {
        super.doAction(params);

        for (var param : params) {
            System.out.println("******doAction2(" + param + ")******");
        }

        return true;
    }

    @Override
    @RuntimeDescriptor("GetSystemInformation#postAction")
    public boolean postAction() {
        super.postAction();

        System.out.println("******postAction2******");

        return true;
    }
}
