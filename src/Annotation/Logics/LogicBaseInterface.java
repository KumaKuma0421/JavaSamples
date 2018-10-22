package Annotation.Logics;

public interface LogicBaseInterface {

    boolean preAction(); // メイン処理の実行前に呼ばれます。

    boolean doAction(Object[] params); // メイン処理となります。

    boolean postAction(); // メイン処理の実行後に呼ばれます。
}
