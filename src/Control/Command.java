package Control;

public interface Command<T> {

    public void executeCommand(T parameters);
}
