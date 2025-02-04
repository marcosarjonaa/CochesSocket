package server.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import server.domain.interfaces.GenericRepositoryInterface;
import server.domain.model.Coche;
import server.domain.model.Usuario;

public class Repository<T> implements GenericRepositoryInterface<T> {
    private AtomicInteger automaticId;
    private AtomicInteger automaticIdUsuario;

    private List<Coche> cocheList;
    private List<Usuario> usuarioList;

    public Repository(){
        automaticId = new AtomicInteger(0);
        cocheList = new ArrayList<Coche>();
        cocheList.add(new Coche(1,"Supra","Toyota"));
        cocheList.add(new Coche(2,"Skyline 34 GT-R ","Nissan"));
        automaticIdUsuario= new AtomicInteger(1);
        usuarioList = new ArrayList<Usuario>();
        usuarioList.add(new Usuario(1, "Marcos", "marcos@gmail.com", "arjona"));
    }

    @Override
    synchronized public void add(T c){
        if (c instanceof Coche) {
            Coche coche = (Coche) c;
            coche.setMatricula(automaticId.incrementAndGet());
            cocheList.add(coche);
        }
    }

    @Override
    synchronized public T get(int i){
        Coche coche = cocheList
            .stream().filter(pos -> pos.getMatricula()==i)
            .findFirst()
            .orElse(null);
        return (T)coche;
    }

    @Override
    synchronized public List<T> getAll(){
        return new ArrayList<>((List<T>) cocheList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByModelo(String modelo) {
        return (List<T>)cocheList.stream()
            .filter(c -> c.getModelo().equalsIgnoreCase(modelo)).findFirst()
            .orElse(null);

    }

    @Override
    public T findByMarcaAndModelo(String marca, String modelo) {
        Coche coche = cocheList
        .stream()
        .filter(
            c -> c.getMarca() == marca && c.getModelo() == modelo
        )
        .findFirst()
        .orElse(null);
        return (T)coche ;
    }

    @Override
    public T findByIdToRemove(int matricula) {
        Coche coche = cocheList
        .stream()
        .filter(c -> c.getMatricula() == matricula)
        .findFirst()
        .orElse(null);
        cocheList.remove(coche); //borro el coche de la lista
        return (T)coche;
    }

    @Override
    public void put(T c){
        if (c instanceof Coche) {
            Coche nuevo = (Coche) c;
            int matricula = nuevo.getMatricula();
            Coche antiguo = cocheList.stream()
                .filter(coche -> coche.getMatricula()==matricula)
                .findFirst()
                .orElse(null);

            if (antiguo==null) {
                antiguo.setModelo(nuevo.getModelo());
                antiguo.setMarca(nuevo.getMarca());
            }

        }
    }

    @Override
    synchronized public T findByEmailAndPassword(String email, String password) {
        Usuario usuario = usuarioList.stream()
        .filter(
            (u) -> u.getEmail().equals(email) &&
                    u.getPassword().equals(password)
        ).findFirst()
        .orElse(null);
        
        return (T) usuario;
    }
}
