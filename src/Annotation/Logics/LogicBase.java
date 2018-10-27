package Annotation.Logics;

import Annotation.InjectionInfo;
import Annotation.RuntimeDescriptor;

@RuntimeDescriptor("BusinessLogic")
public class LogicBase implements LogicBaseInterface {

    @RuntimeDescriptor("stream")
    protected int stream = 0;

    @RuntimeDescriptor("function")
    protected int function = 0;

    @RuntimeDescriptor("constractor")
    public LogicBase() {
        stream = 0;
        function = 0;
    }

    @Override
    @RuntimeDescriptor("preAction")
    public boolean preAction() {
        System.out.println("******preAction1******");
        return true;
    }

    @Override
    @RuntimeDescriptor("doAction")
    @InjectionInfo(stream = 0, function = 0)
    public boolean doAction(Object[] params) {
        for (var param : params) {
            System.out.println("******doAction1(" + param + ")******");
        }

        return true;
    }

    @Override
    @RuntimeDescriptor("postAction")
    public boolean postAction() {
        System.out.println("******postAction1******");
        return true;
    }

    @RuntimeDescriptor("action")
    public boolean action(Object[] params) {
        boolean response = false;

        response = this.preAction();
        if (response) {
            response = this.doAction(params);
            if (response) {
                response = this.postAction();
            }
        }

        return response;
    }
}