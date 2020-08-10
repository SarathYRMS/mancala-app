package com.sara.assessment.mancala.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class MancalaPit implements Serializable {

    private Integer id;
    private Integer stones;

    public Boolean isEmpty() {
        return this.stones == 0;
    }

    public void clear() {
        this.stones = 0;
    }

    public void sow() {
        this.stones++;
    }

    public void addStones(Integer stones) {
        this.stones += stones;
    }

    @Override
    public String toString() {
        return id + ":" + stones;
    }
}
