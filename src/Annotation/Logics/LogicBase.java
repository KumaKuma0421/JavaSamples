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
    public boolean preAction() {
        System.out.println("Start preAction");
        System.out.println("Finish preAction");
        return true;
    }

    @Override
    @RuntimeDescriptor("doAction")
    @InjectionInfo(stream = 0, function = 0)
    public boolean doAction(Object[] params) {

        System.out.println("Start doAction");
        System.out.println("Finish doAction");
        return true;
    }

    @Override
    public boolean postAction() {
        System.out.println("Start postAction");
        System.out.println("Finish postAction");
        return true;
    }
}