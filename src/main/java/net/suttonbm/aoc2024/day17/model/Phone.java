package net.suttonbm.aoc2024.day17.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Phone {
    @Setter
    private List<Integer> program;
    private int idx = 0;
    private long regA;
    private long regB;
    private long regC;

    public Phone(long regA, long regB, long regC) {
        this.regA = regA;
        this.regB = regB;
        this.regC = regC;
    }

    public List<Integer> run() {
        List<Integer> output = new ArrayList<>();

        Instruction next = getNextOperation();
        while (next != null) {
            Integer opOut = doOperation(next).orElse(null);

            if (opOut != null) {
                output.add(opOut);
            }

            next = getNextOperation();
        }

        return output;
    }

    private Optional<Integer> doOperation(Instruction op) {
        int output = -1;
        switch (op.operation()) {
            case 0:
                regA = (long) (regA / Math.floor(Math.pow(2.0, Double.valueOf(op.comboOperand()))));
                break;
            case 1:
                regB = regB ^ op.literalOperand();
                break;
            case 2:
                regB = op.comboOperand() % 8;
                break;
            case 3:
                break;
            case 4:
                regB = regB ^ regC;
                break;
            case 5:
                output = (int) op.comboOperand() % 8;
                break;
            case 6:
                regB = (long) (regA / Math.floor(Math.pow(2.0, Double.valueOf(op.comboOperand()))));
                break;
            case 7:
                regC = (long) (regA / Math.floor(Math.pow(2.0, Double.valueOf(op.comboOperand()))));
                break;
        }

        if (output == -1) {
            return Optional.empty();
        }
        return Optional.of(output);
    }

    private Instruction getNextOperation() {
        if (idx >= program.size()-1) {
            return null;
        }

        int op = program.get(idx);
        int literalOperand = program.get(idx+1);
        int comboOperand = -1;

        switch (literalOperand) {
            case 0, 1, 2, 3:
                comboOperand = literalOperand;
                break;
            case 4:
                comboOperand = (int) regA;
                break;
            case 5:
                comboOperand = (int) regB;
                break;
            case 6:
                comboOperand = (int) regC;
                break;
        }

        Instruction instruction = new Instruction(op, literalOperand, comboOperand);

        switch (program.get(idx)) {
            case 0,1,2,4,5,6,7:
                idx += 2;
                break;
            case 3:
                if (regA == 0) {
                    idx += 2;
                    break;
                } else {
                    idx = program.get(idx+1);
                }
        }

        return instruction;
    }

    @Override
    public String toString() {
        return "Phone [A=%s, B=%s, C=%s]".formatted(regA, regB, regC);
    }
}
