package creamy.mvc;

/**
 * Resultを生成するヘルパメソッドを実装するクラス
 * ヘルパメソッドはControllerで利用される
 * 
 * @author miyabetaiji
 */
public abstract class Results {
    protected Result ok() { return new Result(Status.OK); }

    protected Result ok(Controller controller) {
        return new Result(Status.OK, controller);
    }

    protected Result ok(Object data) {
        return new Result(Status.OK, data);
    }

    protected Result badRequest() { return new Result(Status.BAD_REQUEST); }

    protected Result badRequest(Controller controller) {
        return new Result(Status.BAD_REQUEST, controller);
    }

    protected Result badRequest(Object data) {
        return new Result(Status.BAD_REQUEST, data);
    }
    
    protected Result redirect(String path) {
        return new Result(Status.OK, path);
    }
}
