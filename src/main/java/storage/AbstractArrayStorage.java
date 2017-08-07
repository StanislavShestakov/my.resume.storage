package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage{
    public static final int MAX_LENTH = 10000;
    protected Resume[] storage = new Resume[MAX_LENTH];
    protected int size = 0;


    private boolean isOverflow(){
        if (size == MAX_LENTH){
            return true;
        }
        return false;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected boolean isExist(Object index) {
        if ((Integer)index < 0){
            return false;
        }
        return true;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (isOverflow()) {
            throw new StorageException("Storage owerflov!",r.getUuid());
        }
            insertEl(r, (Integer) searchKey);
            size++;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void doDelete(Object index) {
        fillDeletedEl((Integer) index);
        size--;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void fillDeletedEl(int index);

    protected abstract void insertEl(Resume r, int index);
}
