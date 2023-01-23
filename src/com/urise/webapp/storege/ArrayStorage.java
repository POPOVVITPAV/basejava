package com.urise.webapp.storege;
import com.urise.webapp.modal.Resume;
import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void update(Resume r){
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Resume not found");
        } else {
            storage[index] = r;
        }
    }
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r, String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Error");
        } else if (size == storage.length) {
            System.out.println("Storage is overflow");
        } else {
           storage[size] = r;
           size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume not found");
            return null;
            }
            return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume not found");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i].getUuid(), uuid)) {
            return i;
            }
        }
        return -1;
    }
}
