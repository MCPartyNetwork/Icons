package tech.needvoid.icons.utils;

import java.util.List;

public class ListUtils {

    /**
     * get a sublist of a list with size of list and pagination
     *
     * @param list the list
     * @param value the pagination
     * @param size the size of list
     * @return the list
     */
    public static <T> List<T> getSublist(List<T> list, int value, int size) {
        if(list.isEmpty()) return list;

        int first = Math.min(value * size - size, list.size() - 1);
        int end = Math.min(list.size(), first + size);

        return list.subList(first, end);
    }
}