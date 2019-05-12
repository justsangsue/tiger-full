import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws Exception {

        String[] filanames = {"test", "test1", "test2", "test3", "test5", "test6", "test7", "factorial"};
        for (String filename : filanames) {
            String path = "../tiger_backend/examples/ir/" + filename + ".ir";
            System.out.println("=================================================");
            System.out.println(filename + ".ir");
            NaiveRegisterAllocator ng = new NaiveRegisterAllocator(path);
            ng.generate();
            InstructionSelection is = new InstructionSelection(ng);
            is.generate();
            System.out.println(is.mips);
            String out_path = "../tiger_backend/outputs/" + filename + ".naive.s";
            PrintWriter writer = new PrintWriter(out_path, "UTF-8");
            writer.println(is.mips);
            writer.close();
            System.out.println("=================================================");
        }

        for (String filename : filanames) {
            String path = "../tiger_backend/examples/ir/" + filename + ".ir";
            System.out.println("=================================================");
            System.out.println(filename + ".ir");
            LBBRegisterAllocator lbb = new LBBRegisterAllocator(path);
            lbb.generate();
            InstructionSelection is = new InstructionSelection(lbb);
            is.generate();
            System.out.println(is.mips);
            String out_path = "../tiger_backend/outputs/" + filename + ".lbb.s";
            PrintWriter writer = new PrintWriter(out_path, "UTF-8");
            writer.println(is.mips);
            writer.close();
            System.out.println("=================================================");
        }

    }
}
