package view;

import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import model.Topico;

public class GenericComboModel<T> extends AbstractListModel<T> implements ComboBoxModel<T>{

    private List<T> itemList;
    private T selection;

    public GenericComboModel(List<T> list) {
        this.itemList = list;
    }

    @Override
    public int getSize() {
        return this.itemList.size();
    }

    @Override
    public T getElementAt(int index) {
        return this.itemList.get(index);
    }

    @Override
    public Object getSelectedItem() {
        return this.selection;
    }

    @Override
    public void setSelectedItem(Object item) {
        this.selection = (T) item;
    }
}