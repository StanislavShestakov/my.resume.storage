package storage;

import model.Resume;

import java.util.Arrays;

public class ManualSortedStorage extends AbstractStorage {

    @Override
    protected void fillDeletedEl(int index) {
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
    }

    @Override
    protected void insertEl(Resume r, int index) {
        index = -index -1; // Negative numbers do not start with 0
        if (size == 0) {
            storage[size] = r;
        } else {
            for (int i = 0; i < size - index; i++) {
                storage[size] = storage[size - i -1];
            }
            storage[index] = r;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}
