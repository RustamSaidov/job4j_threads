package ru.job4j.pools;

import java.util.Objects;

public class ObjForSearch {
    private String name;

    public ObjForSearch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjForSearch)) {
            return false;
        }
        ObjForSearch that = (ObjForSearch) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
