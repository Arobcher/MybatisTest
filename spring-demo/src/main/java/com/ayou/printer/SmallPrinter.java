package com.ayou.printer;

public class SmallPrinter extends Printer{
    public SmallPrinter(Ink ink, Size size) {
        super(ink, size);
    }

    @Override
    public void print() {
        System.out.println("Small printer is printing...");
        System.out.println("Ink: " + getInk() + " Size: " + getSize());
    }
}
