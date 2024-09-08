package org.paveltinnik;

import java.util.Comparator;

/**
 * Реализация собственного списка ArrayList, не потокобезопасного.
 *
 * @param <E> Тип элементов, хранимых в списке.
 */
public class MyArrayList<E> {

    private E[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор для создания нового MyArrayList с заданной емкостью.
     *
     * @param capacity Начальная емкость списка.
     * @throws IllegalArgumentException если емкость отрицательна.
     */
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * Конструктор для создания нового MyArrayList с начальной емкостью по умолчанию (10).
     */
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Добавить элемент в конец списка.
     *
     * @param element Элемент, который нужно добавить.
     */
    public void add(E element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    /**
     * Получить элемент по индексу.
     *
     * @param index Индекс элемента.
     * @return Элемент, хранящийся по заданному индексу.
     * @throws IndexOutOfBoundsException если индекс вне границ списка.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return data[index];
    }

    /**
     * Удалить элемент по индексу.
     *
     * @param index Индекс элемента, который нужно удалить.
     * @return Удаленный элемент.
     * @throws IndexOutOfBoundsException если индекс вне границ списка.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        E removedElement = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removedElement;
    }

    /**
     * Очистить список, удалив все элементы.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Получить размер списка (количество элементов).
     *
     * @return Размер списка.
     */
    public int size() {
        return size;
    }

    /**
     * Проверить, пустой ли список.
     *
     * @return true, если список пустой, иначе false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Увеличить емкость массива в два раза, если список полон.
     */
    private void resize() {
        E[] newData = (E[]) new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Отсортировать список с использованием алгоритма Quicksort.
     * Сортировка выполняется по возрастанию, используя интерфейс Comparable,
     * если элементы списка реализуют Comparable.
     *
     * @throws IllegalArgumentException если элементы списка не реализуют Comparable.
     */
    public void quickSort() {
        if (data.length > 0 && data[0] instanceof Comparable) {
            quickSort(0, size - 1, (a, b) -> ((Comparable<E>) a).compareTo(b));
        } else {
            throw new IllegalArgumentException("Elements must implement Comparable interface");
        }
    }

    /**
     * Отсортировать список с использованием алгоритма Quicksort.
     * Сортировка выполняется с использованием переданного Comparator.
     *
     * @param comparator Компаратор для сравнения элементов списка.
     */
    public void quickSort(Comparator<E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Вспомогательный метод для алгоритма Quicksort.
     * Рекурсивно сортирует подмассив списка от low до high индексов.
     *
     * @param low       Начальный индекс подмассива.
     * @param high      Конечный индекс подмассива.
     * @param comparator Компаратор для сравнения элементов.
     */
    private void quickSort(int low, int high, Comparator<E> comparator) {
        if (low < high) {
            int partitionIndex = partition(low, high, comparator);
            quickSort(low, partitionIndex - 1, comparator);
            quickSort(partitionIndex + 1, high, comparator);
        }
    }

    /**
     * Разделение подмассива для алгоритма Quicksort.
     *
     * @param low       Начальный индекс подмассива.
     * @param high      Конечный индекс подмассива.
     * @param comparator Компаратор для сравнения элементов.
     * @return Индекс опорного элемента.
     */
    private int partition(int low, int high, Comparator<E> comparator) {
        E pivot = data[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(data[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Обмен местами двух элементов в массиве.
     *
     * @param i Первый индекс.
     * @param j Второй индекс.
     */
    private void swap(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return Строка, представляющая список.
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        sb.append(data[size - 1]).append("]");
        return sb.toString();
    }
}
