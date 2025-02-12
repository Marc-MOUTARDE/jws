package fr.epita.assistants.item_producer.errors;

public class Result<T, E> {
    private T value;
    private E error;
    private ResultType type;

    public enum ResultType {
        Error,
        Value
    };

    public static<T, E> Result<T, E> error(E error)
    {
        return new Result<>(ResultType.Error, null, error);
    }

    public static<T, E> Result<T, E> success(T value)
    {
        return new Result<>(ResultType.Value, value, null);
    }

    private Result(ResultType type, T value, E error)
    {
        this.type = type;
        this.error = error;
        this.value = value;
    }

    public boolean is_error()
    {
        return this.type == ResultType.Error;
    }

    public T get_value()
    {
        return this.value;
    }

    public E get_error()
    {
        return this.error;
    }
}
