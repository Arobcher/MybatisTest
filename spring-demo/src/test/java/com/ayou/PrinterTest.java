package com.ayou;

import com.ayou.printer.Ink;
import com.ayou.printer.Size;
import com.ayou.printer.SmallPrinter;
import lombok.SneakyThrows;
import org.junit.Test;

import java.net.URLEncoder;

public class PrinterTest {
    @Test
    public void smallPrinter() {
        new SmallPrinter(Ink.COLOR_INK, Size.A4).print();
        new SmallPrinter(Ink.GRAY_INK, Size.B5).print();
    }
}
