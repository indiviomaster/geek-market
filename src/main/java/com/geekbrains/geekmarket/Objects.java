package com.geekbrains.geekmarket;

import java.util.List;

public class Objects {
    int id;
    Integer parentId;
    List <Objects> childrenList;

    @Override
    public String toString() {
        return "Objects{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", List of children=" + childrenList +
                '}';
    }

    public Objects(int id, Integer parentId, List<Objects> childrenList) {
        this.id = id;
        this.parentId = parentId;
        this.childrenList = childrenList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Objects> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Objects> childrenList) {
        this.childrenList = childrenList;
    }

    public void addChildren(Objects ch){
        this.childrenList.add(ch);
    }
    public Objects getChildren(int id) {

        for (Objects child: childrenList){
            if(child.id == id){
                return child;
            }
        }
        return null;

    }
}
