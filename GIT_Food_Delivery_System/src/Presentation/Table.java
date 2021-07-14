package Presentation;
import Business.MenuItem;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.HashSet;

public class Table {
    private TableModel tableModel;
    private static final String[] columnNames =
            {"title","rating","calories","protein", "fat","sodium","price"};

    public Table() {
        tableModel = new DefaultTableModel(columnNames,0);
    }

    public void generateTable(HashSet<MenuItem> allProducts)
    {
        int rows = allProducts.size();
        tableModel = new DefaultTableModel(columnNames, rows){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==0)
                    return false;
                else
                    return true;
            }
        };

        //populez tabelul

        int i = 0, j = 0; //i linie,j coloana
        for (MenuItem item : allProducts)
        {
            ArrayList<String> values=new ArrayList<>();
            values.add(item.getTitle());
            values.add(String.valueOf(item.getRating()));
            values.add(String.valueOf(item.getCalories()));
            values.add(String.valueOf(item.getProtein()));
            values.add(String.valueOf(item.getFat()));
            values.add(String.valueOf(item.getSodium()));
            values.add(String.valueOf(item.getPrice()));

            for (int k=0;k<7;k++) {
                String fieldName = columnNames[k];
                tableModel.setValueAt(values.get(k), i, j);

                j++;
            }
            j = 0;
            i++;
        }
    }

    public void addRow(MenuItem m)
    {//utilizat la adaugarea rand pe rand a unei linii in tabelul cu produse selectate
        //de catre admin in crearea unui produs compus nou

        ((DefaultTableModel)tableModel).addRow(new Object[]{
                m.getTitle(),m.getRating(),m.getCalories(),m.getProtein(),
                m.getFat(),m.getSodium(),m.getPrice()}
        );
    }
        public TableModel getTableModel()
        { return tableModel; }

}
