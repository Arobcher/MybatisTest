package com.ayou.printer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Printer {
    private Ink ink;
    private Size size;
    public abstract void print();
}
