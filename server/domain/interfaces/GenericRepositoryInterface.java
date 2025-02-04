package server.domain.interfaces;

import java.util.List;

public interface GenericRepositoryInterface<T> {
    public void add(T c);

    public T get(int i);

    public List<T> getAll();

    public List<T> findByModelo(String email);

    public T findByMarcaAndModelo(String marca, String modelo);

    public T findByIdToRemove(int id);

    public void put(T c);

    public T findByEmailAndPassword(String email, String password);
}
