package me.dkavila.board.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Position {
    private int row;
    private int column;

    public void setValues(int row, int column){
        this.row = row;
        this.column = column;
    }

}
