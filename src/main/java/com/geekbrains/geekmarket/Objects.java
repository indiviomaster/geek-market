package com.geekbrains.geekmarket;

import java.util.List;

public class Objects {
    int id;
    Integer parId;
    List <Objects> children;

    @Override
    public String toString() {
        return "Objects{" +
                "id=" + id +
                ", parId=" + parId +
                ", children=" + children +
                '}';
    }

    public Objects(int id, Integer parId, List<Objects> children) {
        this.id = id;
        this.parId = parId;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParId() {
        return parId;
    }

    public void setParId(Integer parId) {
        this.parId = parId;
    }

    public List<Objects> getChildren() {
        return children;
    }

    public void setChildren(List<Objects> children) {
        this.children = children;
    }


}
