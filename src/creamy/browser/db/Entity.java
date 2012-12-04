package creamy.browser.db;

import javafx.collections.ObservableList;

public class Entity {
    protected Store store;
    protected Integer id;
    
    public Entity() {
        //store = new Store(this);
        store = EntityManager.getInstance().getStore(this);
    }

    protected void load() {
        if (!store.loaded()) store.load();
    }
    
    public final Integer getId() {
        return id;
    }
    
    public final void setId(Integer id) {
        this.id = id;
    }
    
    public <T> T findById(Integer id) {
        load();
        return (T)store.findById(id);
    }

    public <T> ObservableList<T> findAll() {
        load();
        return store.findAll();
    }    

    public void save() {
        load();
        store.add(this);
        store.save();
    }    
}
